package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class FullTimeEmployeeService {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
	private final GeneralExclude_PersonQualificationService genereralExclude;
	private final PersonQualification_ValidatorService validatorService;
	private final PersonQualification_CreateUri createUri;
	private final PersonQualification_CreateManagerAndEmployeeDto createDto;
	
	public FullTimeEmployeeService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			FullTimeEmployeeRepository fullTimeEmployeeRepository,
			GeneralExclude_PersonQualificationService genereralExclude,
			PersonQualification_ValidatorService validatorService,
			PersonQualification_CreateUri createUri,
			PersonQualification_CreateManagerAndEmployeeDto createDto) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
		this.genereralExclude = genereralExclude;
		this.validatorService = validatorService;
		this.createUri = createUri;
		this.createDto = createDto;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri registerService(
				DtoRecord_FullTimeEmployeeRegistry fullTimeEmployeeRecord,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) 
				throws ResponseStatusException {
		
		validatorService.registerServiceValidation(fullTimeEmployeeRecord.person_Id(), specifiedQualification);
		
		DtoClass_ManagerAndFullTimeEmployeeRegistry fullTimeEmployeeClassDto = 
				new DtoClass_ManagerAndFullTimeEmployeeRegistry(fullTimeEmployeeRecord);

		var fullTimeEmployee = mapper.map(fullTimeEmployeeClassDto, FullTimeEmployeePersonQualification.class);

		var person = personRepository.getReferenceById(fullTimeEmployeeClassDto.getPerson_Id() );
		
		fullTimeEmployee.setPerson(person);
		
		fullTimeEmployee.setInitialDate(LocalDate.now());
		
		personQualificationRepository.save(fullTimeEmployee);
		
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
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri exclude(
				Long person_Id, 
				String specifiedQualification, 
				UriComponentsBuilder uriComponentsBuilder) {
		return genereralExclude.generalExclude(person_Id, specifiedQualification, uriComponentsBuilder);
	}	
}

