package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.domainModel.personDomain.personQualification.SpecifiedQualification.MANAGER;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToListingOfQualification;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToOutputManagerOfRegistry;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecordToRegistryOfManager;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.serviceApplication.personService.PersonQualificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController("managerControllerBean")
@RequestMapping("manager")
@SecurityRequirement(name = "bearer-key")
public class ManagerController {
	private final PersonQualificationService managerService; 
	
	public ManagerController(PersonQualificationService managerService) {
		this.managerService = managerService;
	}
	
	@Autowired
	ManagerRepository manager;
	
	@Autowired
	PersonQualificationRepository qualification;
	
	@Autowired
	ModelMapper mapper;
	
	@PostMapping
	@SuppressWarnings("null")
	public ResponseEntity<DtoClassToOutputManagerOfRegistry> register(
			@RequestBody DtoRecordToRegistryOfManager managerRecord,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		var dtoRecordToOutputManagerOfRegistry_With_Uri = 
				managerService.managerServiceRegistry(MANAGER, managerRecord, uriComponentsBuilder);

		return ResponseEntity
				.created(dtoRecordToOutputManagerOfRegistry_With_Uri.uri())
				.body(dtoRecordToOutputManagerOfRegistry_With_Uri.dtoClassToOutputManagerOfRegistry());
	}
	
	@GetMapping
	public ResponseEntity<Page<DtoClassToListingOfQualification>> listing(
				@PageableDefault(size = 10, sort = {"sector"}) 
				Pageable qualificationPageable) {
		return ResponseEntity
				.ok(managerService.managerServiceListing(MANAGER, qualificationPageable));
	}
}

	