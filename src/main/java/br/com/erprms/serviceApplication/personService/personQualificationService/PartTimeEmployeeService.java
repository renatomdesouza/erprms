package br.com.erprms.serviceApplication.personService.personQualificationService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

import java.net.URI;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.ResponseEntityOutputDtoPage_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.ResponseEntityOutputDto_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto.InputDtoRecord_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClassPage_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClass_PartTimeEmployee;
import br.com.erprms.repositoryAdapter.personRepository.PartTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPersonOfQualification;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PartTimeEmployeeService {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final PartTimeEmployeeRepository partTimeEmployeeRepository;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public PartTimeEmployeeService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			PartTimeEmployeeRepository partTimeEmployeeRepository,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.partTimeEmployeeRepository = partTimeEmployeeRepository;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<OutputDtoClass_PartTimeEmployee> registerService(
				InputDtoRecord_PartTimeEmployee inputDtoRecord_PartTimeEmployee,
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(inputDtoRecord_PartTimeEmployee.person_Id());
		exceptionService.mismatchExceptionBetweenQualifications(inputDtoRecord_PartTimeEmployee.person_Id());
		
		InputDtoClass_PartTimeEmployee inputDtoClass_PartTimeEmployee = 
				new InputDtoClass_PartTimeEmployee(inputDtoRecord_PartTimeEmployee);

		var partTimeEmployee = mapper.map(inputDtoClass_PartTimeEmployee, PartTimeEmployeePersonQualification.class);

		var person = personRepository.getReferenceById(inputDtoClass_PartTimeEmployee.getPerson_Id() );
		
//////////////////////////////////////   =====> exportar para um método em comum		
		partTimeEmployee.setPerson(person);
		partTimeEmployee.setInitialDate(LocalDate.now());
		personQualificationRepository.save(partTimeEmployee);
		statusPersonOfQualification.setStatusUser(person);
/////////////////////////////////////////////////////////////////////////////////		
		
		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										PART_TIME_EMPLOYEE, 
										person.getId());
		
		var outputDtoClass_PartTimeEmployee = 
				new OutputDtoClass_PartTimeEmployee(person, 
													inputDtoClass_PartTimeEmployee, 
													PART_TIME_EMPLOYEE);
		
		var responseEntityOutputDto_PartTimeEmployee =
				new ResponseEntityOutputDto_PartTimeEmployee(	outputDtoClass_PartTimeEmployee,
																uri);
		
		return ResponseEntity
				.created(responseEntityOutputDto_PartTimeEmployee.uri())
				.body(responseEntityOutputDto_PartTimeEmployee.dtoToClass_PartyTimeEmployeeRegistryOutput());
	}
	
	@Transactional   
	@SuppressWarnings("null")
	public ResponseEntity<Page<?>> listingService(
				Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		Page<OutputDtoClassPage_PartTimeEmployee> outputDtoClassPage_PartTimeEmployeePage = 
				partTimeEmployeeRepository
					.findPartTimeEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
					.map(p -> mapper.map(p, OutputDtoClassPage_PartTimeEmployee.class));
		
		URI uri = createUri.uriCreator(uriComponentsBuilder, PART_TIME_EMPLOYEE);
		
		var responseEntityOutputDtoPage_PartTimeEmployee = new ResponseEntityOutputDtoPage_PartTimeEmployee(
																	outputDtoClassPage_PartTimeEmployeePage,
																	uri);
		
		return ResponseEntity
				.created(responseEntityOutputDtoPage_PartTimeEmployee.uri())
				.body(responseEntityOutputDtoPage_PartTimeEmployee.pageableDto());
	}

	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<OutputDtoClass_PartTimeEmployee> update(
				InputDtoRecord_PartTimeEmployee inputDtoRecord_PartTimeEmployee,
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(inputDtoRecord_PartTimeEmployee.person_Id());
		
		InputDtoClass_PartTimeEmployee inputDtoClass_PartTimeEmployee = new InputDtoClass_PartTimeEmployee(inputDtoRecord_PartTimeEmployee);
		
		var person = personRepository.getReferenceById(inputDtoClass_PartTimeEmployee.getPerson_Id());

		var employee = partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person);
		
		mapper.map(inputDtoClass_PartTimeEmployee, employee);
		
		partTimeEmployeeRepository.save(employee);
		
		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										PART_TIME_EMPLOYEE, 
										person.getId());
		
		var outputDtoClass_PartTimeEmployee = 
				new OutputDtoClass_PartTimeEmployee(person, 
													inputDtoClass_PartTimeEmployee, 
													PART_TIME_EMPLOYEE);
		
		var responseEntityOutputDto_PartTimeEmployee =
				new ResponseEntityOutputDto_PartTimeEmployee(	outputDtoClass_PartTimeEmployee,
																uri);
		
		return ResponseEntity
				.created(responseEntityOutputDto_PartTimeEmployee.uri())
				.body(responseEntityOutputDto_PartTimeEmployee.dtoToClass_PartyTimeEmployeeRegistryOutput());

	}
	
	@SuppressWarnings("null")
	@Transactional
	public ResponseEntity<OutputDtoClass_PartTimeEmployee> exclude(
				@NonNull Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		try {	
			PersonQualificationSuperclassEntity personQualificationToDelete = 
					personQualificationRepository.personActiveQualification(person_Id, PART_TIME_EMPLOYEE); 
			
			var personAsActive = personQualificationRepository.personActiveQualification(person_Id, PART_TIME_EMPLOYEE);
			personAsActive.setFinalDate(LocalDate.now());
			personQualificationRepository.save(personAsActive);
			
			@SuppressWarnings("null")
			var person = personRepository.getReferenceById(person_Id);
			statusPersonOfQualification.setSatusNotUser(person);
			
			var uri = createUri.uriCreator(	uriComponentsBuilder, 
											PART_TIME_EMPLOYEE, 
											person_Id);

			var outputDtoClass_PartTimeEmployee = 
					new OutputDtoClass_PartTimeEmployee(personQualificationToDelete, 
														PART_TIME_EMPLOYEE);
			
			var responseEntityOutputDto_PartTimeEmployee =
					new ResponseEntityOutputDto_PartTimeEmployee(	outputDtoClass_PartTimeEmployee,
																	uri);
			
			return ResponseEntity
					.created(responseEntityOutputDto_PartTimeEmployee.uri())
					.body(responseEntityOutputDto_PartTimeEmployee.dtoToClass_PartyTimeEmployeeRegistryOutput());
			
		} catch (NullPointerException ex) { 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no such qualification to be deleted");
		}
	}
}
