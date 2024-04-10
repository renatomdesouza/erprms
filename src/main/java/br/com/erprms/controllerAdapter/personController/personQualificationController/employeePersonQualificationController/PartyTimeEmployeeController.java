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

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto.InputDtoRecord_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClassExclude_PartTimeEmployee;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpPost;
import br.com.erprms.serviceApplication.personService.personQualificationService.PartTimeEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(PART_TIME_EMPLOYEE)
@SecurityRequirement(name = "bearer-key")
public class PartyTimeEmployeeController {
	private final PartTimeEmployeeService partyTimeEmployeeService;
	private final PersonQualificationService_HttpPost personQualificationService_HttpPost;
	
	public PartyTimeEmployeeController(
			PartTimeEmployeeService partyTimeEmployeeService,
			PersonQualificationService_HttpPost personQualificationService_HttpPost) {
		this.partyTimeEmployeeService = partyTimeEmployeeService;
		this.personQualificationService_HttpPost = personQualificationService_HttpPost;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<? extends PersonQualificationOutputDtoInterface> register(   
			@RequestBody InputDtoRecord_PartTimeEmployee partyTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		
		return personQualificationService_HttpPost.registerService(
					new InputDtoClass_PartTimeEmployee(partyTimeEmployeeRecordDto), 
					uriComponentsBuilder,
					PART_TIME_EMPLOYEE);
	}
		
	@GetMapping
	@Transactional
	public ResponseEntity<Page<?>> listing(
				@PageableDefault Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		return partyTimeEmployeeService.listingService(	qualificationPageable,
														uriComponentsBuilder,
														PART_TIME_EMPLOYEE);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<OutputDtoClass_PartTimeEmployee> update(
			@RequestBody InputDtoRecord_PartTimeEmployee partyTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return partyTimeEmployeeService.update(	partyTimeEmployeeRecordDto, 
												uriComponentsBuilder,
												PART_TIME_EMPLOYEE);
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	public ResponseEntity<OutputDtoClassExclude_PartTimeEmployee> exclude( 
				@NonNull @PathVariable Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return partyTimeEmployeeService.exclude(person_Id, 
												uriComponentsBuilder,
												PART_TIME_EMPLOYEE);
	}
}
