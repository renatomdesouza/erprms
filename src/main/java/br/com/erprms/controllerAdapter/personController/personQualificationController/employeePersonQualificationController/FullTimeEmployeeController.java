package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.serviceApplication.personService.personQualificationService.FullTimeEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping(FULL_TIME_EMPLOYEE)
@SecurityRequirement(name = "bearer-key")
public class FullTimeEmployeeController {
	private final FullTimeEmployeeService fullTimeEmployeeService;
	
	
	public FullTimeEmployeeController(
			FullTimeEmployeeService fullTimeEmployeeService) {
		this.fullTimeEmployeeService = fullTimeEmployeeService;
	}

	@PostMapping
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_ManagerAndFullTimeEmployeeRegistryOutput> register(
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
	public ResponseEntity<Page<DtoClass_ManagerAndFullTimeEmployeeToListing>> listing(
				@PageableDefault 
				Pageable qualificationPageable) {
		
		Page<DtoClass_ManagerAndFullTimeEmployeeToListing> fullTimeEmployeePagesDto = 
				fullTimeEmployeeService.listingService(qualificationPageable);
		
		return ResponseEntity.ok(fullTimeEmployeePagesDto);
	}
	
	@DeleteMapping("/{person_Id}")
	public void exclude( @NonNull @PathVariable Long person_Id) {
		fullTimeEmployeeService.exclude(person_Id, FULL_TIME_EMPLOYEE);
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