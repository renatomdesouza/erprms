package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

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

import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoRecord_PartTimeEmployeeRegistry;
import br.com.erprms.serviceApplication.personService.personQualificationService.PartTimeEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(PART_TIME_EMPLOYEE)
@SecurityRequirement(name = "bearer-key")
public class PartyTimeEmployeeController {
	private final PartTimeEmployeeService partyTimeEmployeeService;
	
	public PartyTimeEmployeeController(
			PartTimeEmployeeService partyTimeEmployeeService) {
		this.partyTimeEmployeeService = partyTimeEmployeeService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> register(   
			@RequestBody DtoRecord_PartTimeEmployeeRegistry partyTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return partyTimeEmployeeService.registerService(partyTimeEmployeeRecordDto, 
														uriComponentsBuilder);
	}
		
	@GetMapping
	@Transactional
	public ResponseEntity<Page<DtoClass_PartTimeEmployeeToListing>> listing(
				@PageableDefault Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		return partyTimeEmployeeService.listingService(	qualificationPageable,
														uriComponentsBuilder);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> update(
			@RequestBody DtoRecord_PartTimeEmployeeRegistry partyTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return partyTimeEmployeeService.update(	partyTimeEmployeeRecordDto, 
												uriComponentsBuilder);
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	public ResponseEntity<DtoClass_PartTimeEmployeeRegistryOutput> exclude( 
				@NonNull @PathVariable Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return partyTimeEmployeeService.exclude(person_Id, 
												uriComponentsBuilder);
	}
}
