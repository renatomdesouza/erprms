package br.com.erprms.controllerAdapter.personController;

import jakarta.validation.Valid;
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
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpDelete;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpGet;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPost;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPut;
import static br.com.erprms.serviceApplication.personService.IsNaturalPersonConstant.IS_NATURAL_PERSON;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController("naturalPersonControllerBean")
@RequestMapping("naturalPerson")
@SecurityRequirement(name = "bearer-key")
public class NaturalPersonController {
	private final PersonService_HttpPost<? extends PersonListingDto> personPost;
	private final PersonService_HttpGet<? extends PersonListingDto> personGet;  
	private final PersonService_HttpPut<? extends PersonListingDto> personPut;
	private final PersonService_HttpDelete<? extends PersonListingDto> personDelete;
	
	public NaturalPersonController(
			PersonService_HttpPost<? extends PersonListingDto> personPost, 
			PersonService_HttpGet<? extends PersonListingDto> personGet,
			PersonService_HttpPut<? extends PersonListingDto> personPut,
			PersonService_HttpDelete<? extends PersonListingDto> personDelete) {
		this.personPost = personPost;
		this.personGet = personGet;
		this.personPut = personPut;
		this.personDelete = personDelete;
	}

	@PostMapping
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<? extends PersonListingDto> register(
			@RequestBody @Valid DtoRecord_NaturalPersonOfRegistry naturalPersonOfRecord,
			UriComponentsBuilder uriComponentsBuilder) { 
		
		var dtoRecordToNaturalPerson = personPost.registerService(naturalPersonOfRecord, uriComponentsBuilder);

		return ResponseEntity
				.created(dtoRecordToNaturalPerson.uri())
				.body(dtoRecordToNaturalPerson.dtoOfPerson());	
	}
	
	@GetMapping
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<Page<? extends PersonListingDto>> naturalPersonListing(
			@PageableDefault(size = 10, sort = "fullNameOrEntityName") Pageable naturalPersonPageable,
			UriComponentsBuilder uriComponentsBuilder){
		
		var dtoRecordOfServicePerson_Page = personGet.listingService(
																naturalPersonPageable, 
																uriComponentsBuilder, 
																IS_NATURAL_PERSON);
		return ResponseEntity
				.created(dtoRecordOfServicePerson_Page.uri())
				.body(dtoRecordOfServicePerson_Page.page());
	}
	
	@PutMapping
	@Transactional
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
	@Transactional
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
