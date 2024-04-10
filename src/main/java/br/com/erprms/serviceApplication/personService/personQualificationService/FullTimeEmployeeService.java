package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.net.URI;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.ResponseEntityOutputDtoPage_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.ResponseEntityOutputDto_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoRecord_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutPutExcludeDto_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputPageDtoClass_FullTimeEmployeeAndManager;
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
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public FullTimeEmployeeService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			FullTimeEmployeeRepository fullTimeEmployeeRepository,
			GeneralExclude_PersonQualificationService genereralExclude,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
		this.genereralExclude = genereralExclude;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<?> registerService(  
				InputDtoRecord_FullTimeEmployeeAndManager inputDtoRecord_FullTimeEmployeeAndManager,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(inputDtoRecord_FullTimeEmployeeAndManager.person_Id());
		exceptionService.mismatchExceptionBetweenQualifications(inputDtoRecord_FullTimeEmployeeAndManager.person_Id());
		
		InputDtoClass_FullTimeEmployeeAndManager inputDtoClass_FullTimeEmployeeAndManager = 
				new InputDtoClass_FullTimeEmployeeAndManager(inputDtoRecord_FullTimeEmployeeAndManager);

		var fullTimeEmployee = mapper.map(inputDtoClass_FullTimeEmployeeAndManager, FullTimeEmployeePersonQualification.class);

		var person = personRepository.getReferenceById(inputDtoClass_FullTimeEmployeeAndManager.getPerson_Id() );
		
		fullTimeEmployee.setPerson(person);
		fullTimeEmployee.setInitialDate(LocalDate.now());
		personQualificationRepository.save(fullTimeEmployee);
		statusPersonOfQualification.setStatusUser(person);
		
		var uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification, 
										person.getId());
		
		var outputDtoClass_PartTimeEmployee = 
				new OutputDtoClass_FullTimeEmployeeAndManager(	person, 
																inputDtoClass_FullTimeEmployeeAndManager, 
																specifiedQualification);
		
		var responseEntityOutputDto_FullTimeEmployeeAndManager =
				new ResponseEntityOutputDto_FullTimeEmployeeAndManager(	outputDtoClass_PartTimeEmployee,
																uri);
		
		return ResponseEntity
				.created(responseEntityOutputDto_FullTimeEmployeeAndManager.uri())
				.body(responseEntityOutputDto_FullTimeEmployeeAndManager.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}
	
	@SuppressWarnings("null")
	@Transactional   
	public ResponseEntity<Page<?>> listingService(
				Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) {
		Page<OutputPageDtoClass_FullTimeEmployeeAndManager> outputPageDtoClass_FullTimeEmployeeAndManagerPage = 
				fullTimeEmployeeRepository
					.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
					.map(p -> mapper.map(p, OutputPageDtoClass_FullTimeEmployeeAndManager.class));
		
		var uri = createUri.uriCreator(uriComponentsBuilder, specifiedQualification);

		var responseEntityOutputDtoPage_FullTimeEmployeeAndManager =
				new ResponseEntityOutputDtoPage_FullTimeEmployeeAndManager(
						outputPageDtoClass_FullTimeEmployeeAndManagerPage, 
						uri);
		
		return ResponseEntity
				.created(responseEntityOutputDtoPage_FullTimeEmployeeAndManager.uri())
				.body(responseEntityOutputDtoPage_FullTimeEmployeeAndManager.pageableDto());
	}

	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<?> update(   
				InputDtoRecord_FullTimeEmployeeAndManager employeeAndManagerRecordDtoInput,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(employeeAndManagerRecordDtoInput.person_Id());
		
		InputDtoClass_FullTimeEmployeeAndManager inputDtoClass_FullTimeEmployeeAndManager = 
				new InputDtoClass_FullTimeEmployeeAndManager(employeeAndManagerRecordDtoInput);
		
		var person = personRepository.getReferenceById(inputDtoClass_FullTimeEmployeeAndManager.getPerson_Id());

		var employee = fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person);
		
		mapper.map(inputDtoClass_FullTimeEmployeeAndManager, employee);
		
		fullTimeEmployeeRepository.save(employee);
		
		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification, 
										person.getId());

		var outputDtoClass_PartTimeEmployee = 
				new OutputDtoClass_FullTimeEmployeeAndManager(	person, 
																inputDtoClass_FullTimeEmployeeAndManager, 
																specifiedQualification);
		
		var responseEntityOutputDto_FullTimeEmployeeAndManager =
				new ResponseEntityOutputDto_FullTimeEmployeeAndManager(	outputDtoClass_PartTimeEmployee,
																uri);
		
		return ResponseEntity
				.created(responseEntityOutputDto_FullTimeEmployeeAndManager.uri())
				.body(responseEntityOutputDto_FullTimeEmployeeAndManager.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}
	
	@Transactional
	public ResponseEntity<OutPutExcludeDto_FullTimeEmployeeAndManager> exclude(   
				@NonNull Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) throws ResponseStatusException {
		return genereralExclude.generalExclude(	person_Id,
												uriComponentsBuilder, 
												specifiedQualification);	
	}
}

