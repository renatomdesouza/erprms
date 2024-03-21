package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpPost <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private ModelMapper mapper;

	public PersonService_HttpPost(
			PersonRepository personRepository, 
			ModelMapper mapper) {
		this.personRepository = personRepository;
		this.mapper = mapper;
	}

	@Transactional     
	@SuppressWarnings({ "hiding", "null" })
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> registerService(
				T personDto,	 
				UriComponentsBuilder uriComponentsBuilder) {

		var person = createPerson(personDto);
		
		personRepository.save(person);
		
		var uri = new PersonService_Uri().uriBuild(
							uriComponentsBuilder, 
							person.getId(), 
							person.getIsNaturalPerson());

		var personListingDto = new PersonService_Dto<>(mapper)
				.selectNaturalOrLegalPersonToListing_Dto(person);

		return new DtoRecord_ServicePerson<>(uri, personListingDto);
	}

	@SuppressWarnings("hiding")
	private <T> PersonEntity createPerson(T personDto) {
		
		var person = new PersonEntity();
		
		if (personDto instanceof DtoRecord_NaturalPersonOfRegistry) {
			DtoClass_NaturalPersonOfRegistry dtoNaturalPerson = 
				new DtoClass_NaturalPersonOfRegistry((DtoRecord_NaturalPersonOfRegistry) personDto);

			person = mapper.map(dtoNaturalPerson, PersonEntity.class);
		};
		
		if (personDto instanceof DtoRecord_LegalPersonOfRegistry) {
				DtoClass_LegalPersonOfRegistry dtoLegalPerson = 
						new DtoClass_LegalPersonOfRegistry((DtoRecord_LegalPersonOfRegistry) personDto);
					
				person = mapper.map(dtoLegalPerson, PersonEntity.class);
		};
		
		return person;
	}
}
