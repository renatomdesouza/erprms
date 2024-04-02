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

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoRecord_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputPageDtoClass_FullTimeEmployeeAndManager;
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
	public ResponseEntity<?> register(
			@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return fullTimeEmployeeService.registerService(	fullTimeEmployeeRecordDto, 
														uriComponentsBuilder);
	}

		
	@GetMapping
	@Transactional
	public ResponseEntity<Page<?>> listing(
				@PageableDefault Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		return fullTimeEmployeeService.listingService(	qualificationPageable,
														uriComponentsBuilder);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> update(
			@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return fullTimeEmployeeService.update(	fullTimeEmployeeRecordDto, 
												uriComponentsBuilder);
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	public ResponseEntity<?> exclude( 
				@NonNull @PathVariable Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return	fullTimeEmployeeService.exclude(	person_Id, 
													uriComponentsBuilder);
	}
}
