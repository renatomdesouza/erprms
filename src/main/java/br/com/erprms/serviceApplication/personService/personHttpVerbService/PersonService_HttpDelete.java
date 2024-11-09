package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import br.com.erprms.domainModel.personDomain.PersonsManagementEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.infrastructure.localDateTime_Setter.LocalDateTime_Setter;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.PersonManagement_Service;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Service
public class PersonService_HttpDelete <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private PersonsManagementRepository personsManagementRepository;
	private ModelMapper mapper;
	private final AuthenticatedUsername authenticationFacade;
	private final PersonExceptions personException;
	private LocalDateTime_Setter localDateTime_Setter;

	public PersonService_HttpDelete(
			PersonRepository personRepository,
			PersonsManagementRepository personsManagementRepository,
			ModelMapper mapper,
			AuthenticatedUsername authenticationFacade,
			PersonExceptions personException,
			LocalDateTime_Setter localDateTime_Setter) {
		this.personRepository = personRepository;
		this.personsManagementRepository = personsManagementRepository;
		this.mapper = mapper;
		this.authenticationFacade = authenticationFacade;
		this.personException = personException;
		this.localDateTime_Setter = localDateTime_Setter;
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

		person.setStatusPersonEnum(StatusPersonalUsedEnum.DELETED);
		PersonsManagementEntity personManagement = 
				createManagement(person);
//				createPersonsManagement(person);
				
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

	protected PersonsManagementEntity createManagement(PersonEntity person) {
		return new PersonManagement_Service(this.authenticationFacade, this.localDateTime_Setter).create(person, HttpVerbEnum.DELETE);
	}
//
//	protected PersonsManagementEntity createPersonsManagement(PersonEntity person) {
//		var personManagement = new PersonsManagementEntity();
//		personManagement.setPerson(person);
//		personManagement.setHttpVerb(HttpVerbEnum.DELETE);
//		personManagement.setInitialDate(LocalDateTime.now());
//		personManagement.setLoginUser(authenticationFacade.getAuthenticatedUsername());
//
//		return personManagement;
//	}
}
