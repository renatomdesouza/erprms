package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualifService_HttpGet;
import br.com.erprms.serviceApplication.personService.personQualificationService.FullTimeEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;


@RestController
@RequestMapping("fullTimeEmployee")
@SecurityRequirement(name = "bearer-key")
public class FullTimeEmployeeController {
	private final PersonQualifService_HttpGet personQualifGet;
	private final FullTimeEmployeeService fullTimeEmployeeService;
	
	
	public FullTimeEmployeeController(
			FullTimeEmployeeService fullTimeEmployeeService,
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			PersonQualifService_HttpGet personQualifGet) {
		this.fullTimeEmployeeService = fullTimeEmployeeService;
		this.personQualifGet = personQualifGet;
	}

	@Autowired
	private FullTimeEmployeeRepository fullTimeEmployeeRepository;

	@PostMapping
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_FullTimeEmployeeRegistryOutput> register(
			@RequestBody DtoRecord_FullTimeEmployeeRegistry fullTimeEmployee,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		
		var dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri = 
				fullTimeEmployeeService.registerService(fullTimeEmployee, uriComponentsBuilder, FULL_TIME_EMPLOYEE);

		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}

		
	@GetMapping
	public ResponseEntity<Page<DtoClass_FullTimeEmployeeToListing>> listing(
				@PageableDefault 
				Pageable qualificationPageable) {
		
		return ResponseEntity
				.ok(fullTimeEmployeeService.listingService(qualificationPageable));
	}
}



//@Transactional
//@SuppressWarnings("null")
//public DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri registerService(
//			DtoRecord_FullTimeEmployeeRegistry fullTimeEmployeeRecord,   // DtoRecord_FullTimeEmployeeRegistry
//			UriComponentsBuilder uriComponentsBuilder,
//			String specifiedQualification) 
//			throws ResponseStatusException {
//
//	registerServiceValidation(fullTimeEmployeeRecord.person_Id(), specifiedQualification);
//	
//	DtoClass_FullTimeEmployeeRegistry fullTimeEmployeeDto = 
//			new DtoClass_FullTimeEmployeeRegistry( (DtoRecord_FullTimeEmployeeRegistry) fullTimeEmployeeRecord);
//
//	var fullTimeEmployee = mapper.map(fullTimeEmployeeDto, FullTimeEmployeePersonQualification.class);
//
//	var person = personRepository.getReferenceById(fullTimeEmployeeDto.getPerson_Id() );
//	
//	fullTimeEmployee.setPerson(person);
//	
//	fullTimeEmployee.setInitialDate(LocalDate.now());
//	
//	personQualificationRepository.save(fullTimeEmployee);
//	
//	var uri = uriComponentsBuilder
//				.path("/manager/{id}")
//				.buildAndExpand(person.getId())
//				.toUri();
//	
//	return new DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri(
//				new DtoClass_FullTimeEmployeeRegistryOutput(person, fullTimeEmployee),
//				uri);
//}

//private void registerServiceValidation(@NonNull Long id_Person, String specifiedQualification) {
//	
//	if (!personRepository.existsById(id_Person))
//		throw new ResponseStatusException(
//				HttpStatus.INSUFFICIENT_STORAGE, 
//				"There is no \"Person\" registered with this \"Id\"");
//	
//	Optional<PersonQualificationSuperclassEntity> fullTimeEmployeeOptional = Optional.ofNullable(
//			personQualificationRepository.personActiveQualification(id_Person, specifiedQualification));
//	if (!fullTimeEmployeeOptional.isEmpty())
//		throw new ResponseStatusException(
//				HttpStatus.INSUFFICIENT_STORAGE, 
//				"There is no an active \"FullTimeEmployee\" registry for this Person");
//}