package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpDelete <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private ModelMapper mapper;

	public PersonService_HttpDelete(
			PersonRepository personRepository, 
			ModelMapper mapper) {
		this.personRepository = personRepository;
		this.mapper = mapper;
	}	

	@Transactional
	public DtoRecord_ServicePerson<? extends PersonListingDto> excludeService(
			@NonNull Long id, 
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		
		var person = new PersonEntity();
		
		person = personRepository.getReferenceById(id);
		
		if ( person.getStatusPersonEnum() == StatusPersonalUseEnum.USED ) 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"This person cannot be deleted because there is a qualification registered for them") ; 

		person.setStatusPersonEnum(StatusPersonalUseEnum.DELETED);
		
		personRepository.save(person);
		
		var uri = new PersonService_CreateUri().uriBuild(
						uriComponentsBuilder, 
						person.getId(), 
						person.getIsNaturalPerson());
		
		var personListingDto = new PersonService_CreateDto<>(mapper)
				.selectNaturalOrLegalPersonToListing_Dto(person);
		
		return new DtoRecord_ServicePerson<>(uri, personListingDto);
	}	
}
