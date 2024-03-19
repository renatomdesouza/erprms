package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonPutService <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private ModelMapper mapper;

	public PersonPutService(
			PersonRepository personRepository, 
			ModelMapper mapper) {
		this.personRepository = personRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	@SuppressWarnings("hiding")
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> updateService(
			T personDtoOfRecord,
			@NonNull Long id_person,
			UriComponentsBuilder uriComponentsBuilder) {
		
		var person = personRepository.getReferenceById(id_person);
		
		updatePerson(person, personDtoOfRecord);
		
		personRepository.save(person);
				
		var uri = new PersonService_Uri().uriBuild(
							uriComponentsBuilder, 
							person.getId(), 
							person.getIsNaturalPerson());

		var personListingDto =  new PersonService_Dto<>(mapper)
										.selectNaturalOrLegalPersonToListing_Dto(person); 
		
		return new DtoRecord_ServicePerson<>(uri, personListingDto);
		
	}
	
	@SuppressWarnings("hiding")
	private <T> void updatePerson(PersonEntity person, T personDtoOfRecord) {

		if (personDtoOfRecord instanceof DtoRecord_NaturalPersonOfUpdate) {
			var personUpdateDto = new DtoClass_NaturalPersonOfUpdate((DtoRecord_NaturalPersonOfUpdate) personDtoOfRecord);
			
			mapper.map(personUpdateDto, person);
		};
		
		if ( personDtoOfRecord instanceof DtoRecord_LegalPersonOfUpdate) {
			var personUpdateDto = new DtoClass_LegalPersonOfUpdate((DtoRecord_LegalPersonOfUpdate) personDtoOfRecord);
			
			mapper.map(personUpdateDto, person);
		};
	}
}
