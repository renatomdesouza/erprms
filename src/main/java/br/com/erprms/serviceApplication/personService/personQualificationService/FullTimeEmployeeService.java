package br.com.erprms.serviceApplication.personService.personQualificationService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DtoRecord_FullTimeAndManagerEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPersonOfQualification;
import jakarta.transaction.Transactional;

@Service
public class FullTimeEmployeeService {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
	private final GeneralExclude_PersonQualificationService genereralExclude;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	private final PersonQualification_CreateManagerAndEmployeeDto createDto;
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public FullTimeEmployeeService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			FullTimeEmployeeRepository fullTimeEmployeeRepository,
			GeneralExclude_PersonQualificationService genereralExclude,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			PersonQualification_CreateManagerAndEmployeeDto createDto,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
		this.genereralExclude = genereralExclude;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.createDto = createDto;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri registerService(
				DtoRecord_FullTimeAndManagerEmployeeRegistry fullTimeEmployeeRecordDto,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(fullTimeEmployeeRecordDto.person_Id());
		exceptionService.mismatchExceptionBetweenQualifications(fullTimeEmployeeRecordDto.person_Id());
		
		DtoClass_ManagerAndFullTimeEmployeeRegistry fullTimeEmployeeClassDto = 
				new DtoClass_ManagerAndFullTimeEmployeeRegistry(fullTimeEmployeeRecordDto);

		var fullTimeEmployee = mapper.map(fullTimeEmployeeClassDto, FullTimeEmployeePersonQualification.class);

		var person = personRepository.getReferenceById(fullTimeEmployeeClassDto.getPerson_Id() );
		
		fullTimeEmployee.setPerson(person);
		fullTimeEmployee.setInitialDate(LocalDate.now());
		personQualificationRepository.save(fullTimeEmployee);
		statusPersonOfQualification.setStatusUser(person);
		
		var uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification, 
										person.getId());
		
		var dtoClass_ManagerAndFullTimeEmployeeRegistryOutput = 
				createDto.createManagerAndEmployeeDto(	person, 
														fullTimeEmployeeClassDto, 
														specifiedQualification);

		return new DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri(
						dtoClass_ManagerAndFullTimeEmployeeRegistryOutput,
						uri);
	}
	
	@Transactional   
	public DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri listingService(
				Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) {
		
		var fullTimeEmployeePageDto = fullTimeEmployeeRepository
											.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
											.map(p -> mapper.map(p, DtoClass_ManagerAndFullTimeEmployeeToListing.class));
		
		var uri = createUri.uriCreator(uriComponentsBuilder, specifiedQualification);
		
		return new DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri(
				fullTimeEmployeePageDto,
				uri);
	}

	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri update(
				DtoRecord_FullTimeAndManagerEmployeeRegistry fullTimeEmployeeRecordDto,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(fullTimeEmployeeRecordDto.person_Id());
		
		var fullTimeManagerClassDto = new DtoClass_ManagerAndFullTimeEmployeeRegistry(fullTimeEmployeeRecordDto);
		
		var person = personRepository.getReferenceById(fullTimeManagerClassDto.getPerson_Id());

		var employee = fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person);
		
		mapper.map(fullTimeManagerClassDto, employee);
		
		fullTimeEmployeeRepository.save(employee);
		
		var uri = createUri.uriCreator(	uriComponentsBuilder, 
				specifiedQualification, 
				person.getId());
		
		var dtoClass_ManagerAndFullTimeEmployeeRegistryOutput = 
				createDto.createManagerAndEmployeeDto(	person, 
														fullTimeManagerClassDto, 
														specifiedQualification);

		return new DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri(
						dtoClass_ManagerAndFullTimeEmployeeRegistryOutput,
						uri);
	}
	
	@Transactional
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri exclude(
				@NonNull Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder, 
				String specifiedQualification) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(person_Id);
		
		return genereralExclude.generalExclude(	person_Id, 
												uriComponentsBuilder, 
												specifiedQualification);
	}	
}

