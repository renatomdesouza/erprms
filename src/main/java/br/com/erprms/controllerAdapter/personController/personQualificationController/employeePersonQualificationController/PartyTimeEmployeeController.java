package br.com.erprms.controllerAdapter.personController.personQualificationController.employeePersonQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

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

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualifService_HttpGet;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("partyTimeEmployee")
@SecurityRequirement(name = "bearer-key")
public class PartyTimeEmployeeController {
//	private final PersonQualifService_HttpPost personQualifPost;
	private final PersonQualifService_HttpGet personQualifGet;
	
	public PartyTimeEmployeeController (
//			PersonQualifService_HttpPost personQualifPost,
			PersonQualifService_HttpGet personQualifGet ) {
//		this.personQualifPost = personQualifPost;
		this.personQualifGet = personQualifGet;
	}
	
	@PostMapping
	@SuppressWarnings("null")
	public /*ResponseEntity<DtoClass_FullTimeEmployeeRegistryOutput>*/ void register(
			@RequestBody DtoRecord_FullTimeEmployeeRegistry managerRecord,
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
//		var dtoRecordToOutputManagerOfRegistry_With_Uri = 
//				personQualifPost.registerService(managerRecord, uriComponentsBuilder);

//		return ResponseEntity
//				.created(dtoRecordToOutputManagerOfRegistry_With_Uri.uri())
//				.body(dtoRecordToOutputManagerOfRegistry_With_Uri.dtoClassToOutputManagerOfRegistry());
	}
	
	@GetMapping
	public ResponseEntity<Page<DtoClass_FullTimeEmployeeListing>> listing(
				@PageableDefault(size = 10, sort = {"sector"}) 
				Pageable qualificationPageable) {
		return ResponseEntity
				.ok(personQualifGet.listingService(qualificationPageable));
	}
}
