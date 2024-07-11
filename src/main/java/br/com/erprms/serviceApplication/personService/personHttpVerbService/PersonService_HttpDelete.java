package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import br.com.erprms.domainModel.personDomain.PersonsManagement_Entity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticationFacade;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Service
public class PersonService_HttpDelete <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private PersonsManagementRepository personsManagementRepository;
	private ModelMapper mapper;
	private final AuthenticationFacade authenticationFacade;
	private final PersonExceptions personException;

	public PersonService_HttpDelete(
			PersonRepository personRepository,
			PersonsManagementRepository personsManagementRepository,
			ModelMapper mapper,
			AuthenticationFacade authenticationFacade,
			PersonExceptions personException) {
		this.personRepository = personRepository;
		this.personsManagementRepository = personsManagementRepository;
		this.mapper = mapper;
		this.authenticationFacade = authenticationFacade;
		this.personException = personException;
	}	

	@Transactional
	public DtoRecord_ServicePerson<? extends PersonListingDto> excludeService(
			@NonNull Long id, 
			UriComponentsBuilder uriComponentsBuilder) 
			throws ResponseStatusException {
		
		var person = new PersonEntity();
		
		person = personRepository.getReferenceById(id);
		var statusPerson = person.getStatusPersonEnum();
		personException.personWithStatusInUse(statusPerson);

		person.setStatusPersonEnum(StatusPersonalUseEnum.DELETED);
		var personManagement = setPersonsManagement(person);

		personRepository.save(person);
		personsManagementRepository.save(personManagement);

		var uri = new PersonService_CreateUri().uriBuild(
						uriComponentsBuilder, 
						person.getId(), 
						person.getIsNaturalPerson());
		
		var personListingDto = new PersonService_CreateDto<>(mapper)
				.selectNaturalOrLegalPersonToListing_Dto(person);
		
		return new DtoRecord_ServicePerson<>(uri, personListingDto);
	}

	private PersonsManagement_Entity setPersonsManagement(PersonEntity person) {
		var personManagement = new PersonsManagement_Entity();
		personManagement.setPerson(person);
		personManagement.setHttpVerb(HttpVerbEnum.DELETE);
		personManagement.setInitialDate(LocalDateTime.now());
		personManagement.setLoginUser(authenticationFacade.getAuthentication());

		return personManagement;
	}
}
