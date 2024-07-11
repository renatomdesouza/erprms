package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfListing;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpGet <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private ModelMapper mapper;

	public PersonService_HttpGet(
			PersonRepository personRepository, 
			ModelMapper mapper) {
		this.personRepository = personRepository;
		this.mapper = mapper;
	}

	@Transactional
	public DtoRecord_ServicePerson_Page<? extends PersonListingDto>  listingService( 
				Pageable personPageable, 
				UriComponentsBuilder uriComponentsBuilder,
				Boolean isNaturalPerson) {
		
		var personListingDto_Page = setterPersonListingDtoPage(personPageable, isNaturalPerson);
		
		URI uri = new PersonService_CreateUri().uriBuild(uriComponentsBuilder, isNaturalPerson);
		
		return new DtoRecord_ServicePerson_Page<>(uri, personListingDto_Page);
	}

	private Page<? extends PersonListingDto> setterPersonListingDtoPage(
			Pageable personPageable,
			Boolean isNaturalPerson) {
		
		Page<? extends PersonListingDto> personListingDto_Page = null; 
		
		if (isNaturalPerson)
				personListingDto_Page = 
					personRepository.findByIsNaturalPersonTrue(personPageable)
						.map(p -> mapper.map(p, DtoClass_NaturalPersonOfListing.class));
		
		if (!isNaturalPerson)
				personListingDto_Page = 
					personRepository.findByIsNaturalPersonFalse(personPageable)
						.map(p -> mapper.map(p, DtoClass_LegalPersonOfListing.class));
		
		return personListingDto_Page;
	}
	
}
