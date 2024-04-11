package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

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
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpGet;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualificationService_HttpPost;
import br.com.erprms.serviceApplication.personService.personQualificationService.ManagerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController("managerControllerBean")
@RequestMapping(MANAGER)
@SecurityRequirement(name = "bearer-key")
public class ManagerController {
	private final ManagerService managerService;
	private final PersonQualificationService_HttpPost personQualificationService_HttpPost;
	private final PersonQualificationService_HttpGet personQualificationService_HttpGet;
	
	public ManagerController (
			ManagerService managerService,
			PersonQualificationService_HttpPost personQualificationService_HttpPost,
			PersonQualificationService_HttpGet personQualificationService_HttpGet) {
		this.managerService = managerService;
		this.personQualificationService_HttpPost = personQualificationService_HttpPost;
		this.personQualificationService_HttpGet = personQualificationService_HttpGet;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<? extends PersonQualificationOutputDtoInterface> register(
			@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeManagerRecordDto,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		
		return personQualificationService_HttpPost.registerService(	
					new InputDtoClass_FullTimeEmployeeAndManager(fullTimeManagerRecordDto), 
					uriComponentsBuilder,
					MANAGER);
	}
	
	@GetMapping
	@Transactional
	public ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>> listing(
				@PageableDefault(size = 10, sort = {"sector"}) Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder) {
		return personQualificationService_HttpGet.listingService(	qualificationPageable,
																	uriComponentsBuilder,
																	MANAGER);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> update(
			@RequestBody InputDtoRecord_FullTimeEmployeeAndManager fullTimeManagerRecordDto,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return	managerService.update(	fullTimeManagerRecordDto, 
										uriComponentsBuilder,
										MANAGER);
	}
	
	@DeleteMapping("/{person_Id}")
	@Transactional
	public ResponseEntity<?> exclude( 
			@NonNull @PathVariable Long person_Id,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException {
		return managerService.exclude(	person_Id, 
										uriComponentsBuilder,
										MANAGER);
	}
}
	