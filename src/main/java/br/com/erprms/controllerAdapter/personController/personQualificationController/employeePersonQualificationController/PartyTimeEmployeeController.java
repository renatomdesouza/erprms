package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeAndManagerEmployeeRegistry;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("partyTimeEmployee")
@SecurityRequirement(name = "bearer-key")
public class PartyTimeEmployeeController {
	
	@PostMapping
	@SuppressWarnings("null")
	public /*ResponseEntity<DtoClass_FullTimeEmployeeRegistryOutput>*/ void register(
			@RequestBody DtoRecord_FullTimeAndManagerEmployeeRegistry managerRecord,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
//		var dtoRecordToOutputManagerOfRegistry_With_Uri = 
//				personQualifPost.registerService(managerRecord, uriComponentsBuilder);

//		return ResponseEntity
//				.created(dtoRecordToOutputManagerOfRegistry_With_Uri.uri())
//				.body(dtoRecordToOutputManagerOfRegistry_With_Uri.dtoClassToOutputManagerOfRegistry());
	}
	
//	@GetMapping
//	public ResponseEntity<Page<DtoClass_ManagerEmployeeListing>> listing(
//				@PageableDefault(size = 10, sort = {"sector"}) 
//				Pageable qualificationPageable) {
//		return ResponseEntity
//				.ok(personQualifGet.listingService(qualificationPageable));
//	}
}
