package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.*;
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

import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification_Page;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoRecord_FullTimeEmployeeAndManager;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController("managerControllerBean")
@RequestMapping(MANAGER)
@SecurityRequirement(name = "bearer-key")
public class ManagerController {
	private final PersonQualificationService_HttpPost personQualificationService_HttpPost;
	private final PersonQualificationService_HttpGet personQualificationService_HttpGet;
	private final PersonQualificationService_HttpPut personQualificationService_HttpPut;
	private final PersonQualificationService_HttpDelete personQualificationService_HttpDelete;
	
	public ManagerController (
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
				@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeManagerRecordDto,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> dtoRecord_ServicePersonQualification = 
				personQualificationService_HttpPost.registerService(	new InputDtoClass_FullTimeEmployeeAndManager(fullTimeManagerRecordDto), 
																		uriComponentsBuilder,
																		MANAGER);
		
		return ResponseEntity
			      .created(dtoRecord_ServicePersonQualification.uri())
			      .body(dtoRecord_ServicePersonQualification.dtoOfPerson());
	}
	
	@GetMapping
	@Transactional
	public ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>> listing(
				@PageableDefault(size = 10, sort = {"sector"}) Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		DtoRecord_ServicePersonQualification_Page<?> responseEntityOutputDtoPage = 
				personQualificationService_HttpGet.listingService(	qualificationPageable,
																	uriComponentsBuilder,
																	MANAGER);
		
		return ResponseEntity
				.created(responseEntityOutputDtoPage.uri())
				.body(responseEntityOutputDtoPage.page());
		
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<? extends PersonQualificationOutputDtoInterface> update(
				@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeManagerRecordDto,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> dtoRecord_ServicePersonQualification = 
				personQualificationService_HttpPut.update(	new InputDtoClass_FullTimeEmployeeAndManager(fullTimeManagerRecordDto), 
															uriComponentsBuilder,
															MANAGER);
		
		return ResponseEntity
			      .created(dtoRecord_ServicePersonQualification.uri())
			      .body(dtoRecord_ServicePersonQualification.dtoOfPerson());
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	public ResponseEntity<? extends PersonQualificationOutputDtoInterface> exclude( 
				@NonNull @PathVariable Long person_Id,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> dtoRecord_ServicePersonQualification = 
				personQualificationService_HttpDelete.exclude(	person_Id, 
																uriComponentsBuilder,
																MANAGER);
		
		return ResponseEntity
			      .created(dtoRecord_ServicePersonQualification.uri())
			      .body(dtoRecord_ServicePersonQualification.dtoOfPerson());
	}
}
	