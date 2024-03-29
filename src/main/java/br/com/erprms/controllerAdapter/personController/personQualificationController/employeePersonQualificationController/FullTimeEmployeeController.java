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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeAndManagerEmployeeRegistry;
import br.com.erprms.serviceApplication.personService.personQualificationService.FullTimeEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

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
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_ManagerAndFullTimeEmployeeRegistryOutput> register(
			@RequestBody DtoRecord_FullTimeAndManagerEmployeeRegistry fullTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		
		var dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri = 
				fullTimeEmployeeService.registerService(	fullTimeEmployeeRecordDto, 
															uriComponentsBuilder, 
															FULL_TIME_EMPLOYEE);

		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}

		
	@GetMapping
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<Page<DtoClass_ManagerAndFullTimeEmployeeToListing>> listing(
				@PageableDefault Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		
		var dtoRecord_FullTimeEmployeeOutputPage_With_Uri =	
				fullTimeEmployeeService.listingService(	qualificationPageable,
														uriComponentsBuilder,
														FULL_TIME_EMPLOYEE);
		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputPage_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputPage_With_Uri.pageableDto());
	}
	
	@PutMapping
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_ManagerAndFullTimeEmployeeRegistryOutput> update(
			@RequestBody DtoRecord_FullTimeAndManagerEmployeeRegistry fullTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {

		var dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri = 
				fullTimeEmployeeService.update(	fullTimeEmployeeRecordDto, 
												uriComponentsBuilder, 
												FULL_TIME_EMPLOYEE);

		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_ManagerAndFullTimeEmployeeRegistryOutput> exclude( 
				@NonNull @PathVariable Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		
		var dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri =
				fullTimeEmployeeService.exclude(person_Id, 
												uriComponentsBuilder, 
												FULL_TIME_EMPLOYEE);
		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}
}
