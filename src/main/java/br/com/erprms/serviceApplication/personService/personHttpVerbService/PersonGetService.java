package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClassToLegalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClassToNaturalPersonOfListing;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonGetService <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private ModelMapper mapper;

	public PersonGetService(
			PersonRepository personRepository, 
			ModelMapper mapper) {
		this.personRepository = personRepository;
		this.mapper = mapper;
	}

	@Transactional
	public DtoRecord_ServicePerson_Page<? extends PersonListingDto>  personListingService( 
				Pageable personPageable, 
				UriComponentsBuilder uriComponentsBuilder,
				Boolean isNaturalPerson) {
		
		var personListingDto_Page = setterPersonListingDtoPage(personPageable, isNaturalPerson);

		URI uri = new PersonService_Uri().uriBuild(uriComponentsBuilder, isNaturalPerson);
		
		return new DtoRecord_ServicePerson_Page<>(uri, personListingDto_Page);
	}

	private Page<? extends PersonListingDto> setterPersonListingDtoPage(
			Pageable personPageable,
			Boolean isNaturalPerson) {
		
		Page<? extends PersonListingDto> personListingDto_Page = null; 
		
		if (isNaturalPerson)
				personListingDto_Page = 
					personRepository.findByIsNaturalPersonTrue(personPageable)
						.map(p -> mapper.map(p, DtoClassToNaturalPersonOfListing.class));
		
		if (!isNaturalPerson)
				personListingDto_Page = 
					personRepository.findByIsNaturalPersonFalse(personPageable)
						.map(p -> mapper.map(p, DtoClassToLegalPersonOfListing.class));
		
		return personListingDto_Page;
	}
	
}
