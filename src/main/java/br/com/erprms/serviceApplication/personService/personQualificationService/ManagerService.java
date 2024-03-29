package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeAndManagerEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPersonOfQualification;
import jakarta.transaction.Transactional;

@Service
public class ManagerService {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final ManagerRepository managerRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final GeneralExclude_PersonQualificationService genereralExclude;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	private final PersonQualification_CreateManagerAndEmployeeDto createDto;
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public ManagerService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			ManagerRepository managerRepository,
			PersonQualificationRepository personQualificationRepository,
			GeneralExclude_PersonQualificationService genereralExclude,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			PersonQualification_CreateManagerAndEmployeeDto createDto,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.genereralExclude = genereralExclude;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.createDto = createDto;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}

	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri registerService(
				DtoRecord_FullTimeAndManagerEmployeeRegistry fullTimeManagerRecordDto, //
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) throws ResponseStatusException {
		
		exceptionService.exceptionRegisterServiceForManagerAndFullTimeEmployee(fullTimeManagerRecordDto.person_Id(), specifiedQualification);
		
		var fullTimeManagerClassDto = new DtoClass_ManagerAndFullTimeEmployeeRegistry(fullTimeManagerRecordDto);

		var person = personRepository.getReferenceById(fullTimeManagerClassDto.getPerson_Id());
		
		var managerEntity = mapper.map(fullTimeManagerClassDto, ManagerPersonQualification.class);

		managerEntity.setPerson(person);
		managerEntity.setInitialDate(LocalDate.now());
		personQualificationRepository.save(managerEntity);
		statusPersonOfQualification.setStatusUser(person);
		
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
	public DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri listingService(
						Pageable qualificationPageable,
						UriComponentsBuilder uriComponentsBuilder,
						String specifiedQualification) {  
		
		var managerPageDto = managerRepository
				.findManagerPersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(p -> mapper.map(p, DtoClass_ManagerAndFullTimeEmployeeToListing.class));

		var uri = createUri.uriCreator(uriComponentsBuilder, specifiedQualification);
		
		return new DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri(managerPageDto, uri);
	
	}

	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri update(
			DtoRecord_FullTimeAndManagerEmployeeRegistry fullTimeManagerRecordDto,
			UriComponentsBuilder uriComponentsBuilder,
			String specifiedQualification) throws ResponseStatusException {
		
		exceptionService.registerServiceExceptionToUpdateAndExclude(fullTimeManagerRecordDto.person_Id(), specifiedQualification);
		
		var fullTimeManagerClassDto = new DtoClass_ManagerAndFullTimeEmployeeRegistry(fullTimeManagerRecordDto);
		
		var person = personRepository.getReferenceById(fullTimeManagerClassDto.getPerson_Id());

		var manager = managerRepository.findManagerEmployeePersonQualificationByFinalDateIsNullAndPerson(person);
		
		mapper.map(fullTimeManagerClassDto, manager);
		
		managerRepository.save(manager);
		
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
		
		return genereralExclude.generalExclude(person_Id, uriComponentsBuilder, specifiedQualification);
	}
}

