package br.com.erprms.controllerAdapter.personController;

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

import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfUpdate;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpDelete;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpGet;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPost;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPut;
import static br.com.erprms.serviceApplication.personService.IsNaturalPersonConstant.IS_NATURAL_PERSON;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController("legalPersonControllerBean")
@RequestMapping("legalPerson")
@SecurityRequirement(name = "bearer-key")
public class LegalPersonController {
	private final PersonService_HttpPost<? extends PersonListingDto> personPost;
	private final PersonService_HttpGet<? extends PersonListingDto> personGet;
	private final PersonService_HttpPut<? extends PersonListingDto> personPut;
	private final PersonService_HttpDelete<? extends PersonListingDto> personDelete;
	
	public LegalPersonController(
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
			@RequestBody DtoRecord_LegalPersonOfRegistry legalPersonOfRecord, 
			UriComponentsBuilder uriComponentsBuilder) { 
	
		var dtoRecordTolegalPerson = personPost.registerService(legalPersonOfRecord, uriComponentsBuilder);
		
		return   ResponseEntity
						.created(dtoRecordTolegalPerson.uri())
						.body(dtoRecordTolegalPerson.dtoOfPerson());	
	}
	
	@GetMapping
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<Page<? extends PersonListingDto>> legalPersonListing(
			@PageableDefault(size = 10, sort = "fullNameOrEntityName") Pageable naturalPersonPageable,
			UriComponentsBuilder uriComponentsBuilder) {
		
		var dtoRecordOfServicePerson_Page = personGet.listingService(
												naturalPersonPageable, 
												uriComponentsBuilder, 
												!IS_NATURAL_PERSON);
		
		return ResponseEntity
				.created(dtoRecordOfServicePerson_Page.uri())
				.body(dtoRecordOfServicePerson_Page.page());
	}
	
	@PutMapping
	@Transactional
	@SuppressWarnings("null")
	public ResponseEntity<? extends PersonListingDto> update(
			@RequestBody DtoRecord_LegalPersonOfUpdate personUpdateRecordDto,
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
			@PathVariable @NonNull Long id,
			UriComponentsBuilder uriComponentsBuilder) throws ResponseStatusException{
		
		var dtoRecordToLegalPerson = personDelete.excludeService(id, uriComponentsBuilder);
		
		return ResponseEntity
				.created(dtoRecordToLegalPerson.uri())
				.body(dtoRecordToLegalPerson.dtoOfPerson());
	}
}
