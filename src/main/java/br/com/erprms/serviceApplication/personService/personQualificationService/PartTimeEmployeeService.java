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

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoRecord_PartTimeEmployeeOutputPage_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoRecord_PartTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoRecord_PartTimeEmployeeRegistry;
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
	public ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> registerService(
				DtoRecord_PartTimeEmployeeRegistry partTimeEmployeeRecordDto,
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(partTimeEmployeeRecordDto.person_Id());
		exceptionService.mismatchExceptionBetweenManagerAndEmployees(partTimeEmployeeRecordDto.person_Id(), PART_TIME_EMPLOYEE);
		
		DtoClass_PartTimeEmployeeRegistry partTimeEmployeeClassDto = 
				new DtoClass_PartTimeEmployeeRegistry(partTimeEmployeeRecordDto);

		var partTimeEmployee = mapper.map(partTimeEmployeeClassDto, PartTimeEmployeePersonQualification.class);

		var person = personRepository.getReferenceById(partTimeEmployeeClassDto.getPerson_Id() );
		
//////////////////////////////////////   =====> exportar para um método em comum		
		partTimeEmployee.setPerson(person);
		partTimeEmployee.setInitialDate(LocalDate.now());
		personQualificationRepository.save(partTimeEmployee);
		statusPersonOfQualification.setStatusUser(person);
/////////////////////////////////////////////////////////////////////////////////		
		
		var uri = createUri.uriCreator(	uriComponentsBuilder, 
										PART_TIME_EMPLOYEE, 
										person.getId());
		return outPutResponseEntity(
				outPutDtoCreatorOfService(PART_TIME_EMPLOYEE, partTimeEmployeeClassDto, person, uri));
	}
	
	@Transactional   
	public ResponseEntity<Page<DtoClass_PartTimeEmployeeToListing>> listingService(
				Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		var partTimeEmployeePageDto = partTimeEmployeeRepository
											.findPartTimeEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
											.map(p -> mapper.map(p, DtoClass_PartTimeEmployeeToListing.class));
		var uri = createUri.uriCreator(uriComponentsBuilder, PART_TIME_EMPLOYEE);
		
		return outPutResponseEntity(
				new DtoRecord_PartTimeEmployeeOutputPage_With_Uri(
						partTimeEmployeePageDto,
						uri));
	}

	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> update(
				DtoRecord_PartTimeEmployeeRegistry partTimeEmployeeRecordDto,
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(partTimeEmployeeRecordDto.person_Id());
		
		var partTimeManagerClassDto = new DtoClass_PartTimeEmployeeRegistry(partTimeEmployeeRecordDto);
		
		var person = personRepository.getReferenceById(partTimeManagerClassDto.getPerson_Id());

		var employee = partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person);
		
		mapper.map(partTimeManagerClassDto, employee);
		
		partTimeEmployeeRepository.save(employee);
		
		var uri = createUri.uriCreator(	uriComponentsBuilder, 
										PART_TIME_EMPLOYEE, 
										person.getId());
		return outPutResponseEntity(
				outPutDtoCreatorOfService(PART_TIME_EMPLOYEE, partTimeManagerClassDto, person, uri));
	}
	
	@Transactional
	public ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> exclude(
				@NonNull Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		try {	
			var personQualificationToDelete = personQualificationRepository.personActiveQualification(person_Id, PART_TIME_EMPLOYEE); 
			
			var personAsActive = personQualificationRepository.personActiveQualification(person_Id, PART_TIME_EMPLOYEE);
			personAsActive.setFinalDate(LocalDate.now());
			personQualificationRepository.save(personAsActive);
			
			@SuppressWarnings("null")
			var person = personRepository.getReferenceById(person_Id);
			statusPersonOfQualification.setSatusNotUser(person);
			
			var uri = createUri.uriCreator(	
							uriComponentsBuilder, 
							PART_TIME_EMPLOYEE, 
							person_Id);
			var dtoClass_PartTimeEmployeeRegistryOutput = 
					new DtoClass_PartTimeEmployeeRegistryOutput(personQualificationToDelete, PART_TIME_EMPLOYEE);
			return outPutResponseEntity(
					new DtoRecord_PartTimeEmployeeOutputRegistry_With_Uri(
										dtoClass_PartTimeEmployeeRegistryOutput,
										uri));
		} catch (NullPointerException ex) { 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no such qualification to be deleted");
		}
	}

	private DtoRecord_PartTimeEmployeeOutputRegistry_With_Uri outPutDtoCreatorOfService(
				String specifiedQualification,
				DtoClass_PartTimeEmployeeRegistry partTimeEmployeeClassDto, 
				PersonEntity person, 
				URI uri) {
		var dtoClass_PartTimeEmployeeRegistryOutput = 
				new DtoClass_PartTimeEmployeeRegistryOutput(person, 
															partTimeEmployeeClassDto, 
															specifiedQualification);
		return new DtoRecord_PartTimeEmployeeOutputRegistry_With_Uri(
				dtoClass_PartTimeEmployeeRegistryOutput,
				uri);
	}
	
	@SuppressWarnings("null")
	private ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> outPutResponseEntity(
			DtoRecord_PartTimeEmployeeOutputRegistry_With_Uri dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri) {
		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.dtoToClass_PartyTimeEmployeeRegistryOutput());
	}
	
	@SuppressWarnings("null")
	private ResponseEntity<Page<DtoClass_PartTimeEmployeeToListing>> outPutResponseEntity(
			DtoRecord_PartTimeEmployeeOutputPage_With_Uri dtoRecord_PartTimeEmployeeOutputPage_With_Uri) {
		return ResponseEntity
				.created(dtoRecord_PartTimeEmployeeOutputPage_With_Uri.uri())
				.body(dtoRecord_PartTimeEmployeeOutputPage_With_Uri.pageableDto());
	}
}
