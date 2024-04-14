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

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoRecord_FullTimeEmployeeAndManager;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpDelete;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpGet;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpPost;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpPut;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(FULL_TIME_EMPLOYEE)
@SecurityRequirement(name = "bearer-key")
public class FullTimeEmployeeController {
	private final PersonQualificationService_HttpPost personQualificationService_HttpPost;
	private final PersonQualificationService_HttpGet personQualificationService_HttpGet;
	private final PersonQualificationService_HttpPut personQualificationService_HttpPut;
	private final PersonQualificationService_HttpDelete personQualificationService_HttpDelete;
	
	public FullTimeEmployeeController(
			PersonQualificationService_HttpPost personQualificationService_HttpPost,
			PersonQualificationService_HttpGet personQualificationService_HttpGet,
			PersonQualificationService_HttpPut personQualificationService_HttpPut,
			PersonQualificationService_HttpDelete personQualificationService_HttpDelete) {
		this.personQualificationService_HttpPost = personQualificationService_HttpPost;
		this.personQualificationService_HttpGet = personQualificationService_HttpGet;
		this.personQualificationService_HttpPut = personQualificationService_HttpPut;
		this.personQualificationService_HttpDelete = personQualificationService_HttpDelete;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<? extends PersonQualificationOutputDtoInterface> register(
			@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		return personQualificationService_HttpPost.registerService(	
					new InputDtoClass_FullTimeEmployeeAndManager(fullTimeEmployeeRecordDto), 
					uriComponentsBuilder,
					FULL_TIME_EMPLOYEE);
	}
		
	@GetMapping
	@Transactional
	public ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>> listing(
				@PageableDefault Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		return personQualificationService_HttpGet.listingService(	qualificationPageable,
																	uriComponentsBuilder,
																	FULL_TIME_EMPLOYEE);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<? extends PersonQualificationOutputDtoInterface> update(
			@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeEmployeeRecordDto,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		return personQualificationService_HttpPut.update(	
					new InputDtoClass_FullTimeEmployeeAndManager(fullTimeEmployeeRecordDto), 
					uriComponentsBuilder,
					FULL_TIME_EMPLOYEE);
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	public ResponseEntity<?> exclude( 
				@NonNull @PathVariable Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return	personQualificationService_HttpDelete.exclude(	person_Id, 
																uriComponentsBuilder,
																FULL_TIME_EMPLOYEE);
	}
}
