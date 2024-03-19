package br.com.erprms.controllerAdapter.personController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonDeleteService;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonGetService;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonPostService;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonPutService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController("naturalPersonControllerBean")
@RequestMapping("naturalPerson")
@SecurityRequirement(name = "bearer-key")
public class NaturalPersonController {
	private final PersonPostService<? extends PersonListingDto> personPost;
	private final PersonGetService<? extends PersonListingDto> personGet;  
	private final PersonPutService<? extends PersonListingDto> personPut;
	private final PersonDeleteService<? extends PersonListingDto> personDelete;
	
	public NaturalPersonController(
			PersonPostService<? extends PersonListingDto> personPost, 
			PersonGetService<? extends PersonListingDto> personGet,
			PersonPutService<? extends PersonListingDto> personPut,
			PersonDeleteService<? extends PersonListingDto> personDelete) {
		this.personPost = personPost;
		this.personGet = personGet;
		this.personPut = personPut;
		this.personDelete = personDelete;
	}

	@PostMapping
	@SuppressWarnings("null")
	public ResponseEntity<? extends PersonListingDto> register(
			@RequestBody DtoRecord_NaturalPersonOfRegistry naturalPersonOfRecord, 
			UriComponentsBuilder uriComponentsBuilder) { 
		
		var dtoRecordToNaturalPerson = personPost.registerService(naturalPersonOfRecord, uriComponentsBuilder);

		return ResponseEntity
				.created(dtoRecordToNaturalPerson.uri())
				.body(dtoRecordToNaturalPerson.dtoOfPerson());	
	}
	
	@GetMapping
	@SuppressWarnings("null")
	public ResponseEntity<Page<? extends PersonListingDto>> naturalPersonListing(
			@PageableDefault(size = 10, sort = "fullNameOrEntityName") Pageable naturalPersonPageable,
			UriComponentsBuilder uriComponentsBuilder){
		
		final Boolean isNaturalPerson = true;
		
		var dtoRecordOfServicePerson_Page = personGet.personListingService(
																naturalPersonPageable, 
																uriComponentsBuilder, 
																isNaturalPerson);
		return ResponseEntity
				.created(dtoRecordOfServicePerson_Page.uri())
				.body(dtoRecordOfServicePerson_Page.page());
	}
	
	@PutMapping
	@SuppressWarnings("null")
	public ResponseEntity<? extends PersonListingDto> update(
			@RequestBody DtoRecord_NaturalPersonOfUpdate personUpdateRecordDto,
			UriComponentsBuilder uriComponentsBuilder) {
		
		var dtoRecordOfServicePerson = personPut.updateService(
													personUpdateRecordDto,
													personUpdateRecordDto.id(),
													uriComponentsBuilder);
		
		return ResponseEntity
				.created(dtoRecordOfServicePerson.uri())
				.body(dtoRecordOfServicePerson.dtoOfPerson());
	}
		
	@DeleteMapping("/{id}")
	@SuppressWarnings("null")
    public ResponseEntity<? extends PersonListingDto> exclude(
    		@PathVariable Long id, 
    		UriComponentsBuilder uriComponentsBuilder) 
    		throws ResponseStatusException {

		var dtoRecordToNaturalPerson = personDelete.excludeService(id, uriComponentsBuilder);
		
		return ResponseEntity
				.created(dtoRecordToNaturalPerson.uri())
				.body(dtoRecordToNaturalPerson.dtoOfPerson());
	}
}
