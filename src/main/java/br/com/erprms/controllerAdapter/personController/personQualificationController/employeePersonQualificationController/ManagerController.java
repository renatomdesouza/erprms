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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.serviceApplication.personService.personQualificationService.ManagerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController("managerControllerBean")
@RequestMapping("manager")
@SecurityRequirement(name = "bearer-key")
public class ManagerController {
	private final ManagerService managerService;
	
	public ManagerController (ManagerService managerService) {
		this.managerService = managerService;
	}
	
	@PostMapping
	@SuppressWarnings("null")
	public ResponseEntity<DtoClass_FullTimeEmployeeRegistryOutput> register(
			@RequestBody DtoRecord_FullTimeEmployeeRegistry managerRecord,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		var dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri = 
				managerService.registerService(managerRecord, uriComponentsBuilder);

		return ResponseEntity
				.created(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.uri())
				.body(dtoRecord_FullTimeEmployeeOutputRegistry_With_Uri.dtoClassToOutputFullTimeEmployeeOfRegistry());
	}
	
	@GetMapping
	public ResponseEntity<Page<DtoClass_FullTimeEmployeeListing>> listing(
				@PageableDefault(size = 10, sort = {"sector"}) 
				Pageable qualificationPageable) {
		return ResponseEntity
				.ok(managerService.listingService(qualificationPageable));
	}

	@DeleteMapping("/{id}")
	public void exclude( @NonNull @PathVariable Long id) {
		managerService.exclude(id, MANAGER);
	}
	
	
}

	