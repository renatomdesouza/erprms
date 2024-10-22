package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.PersonsManagementEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.CreatePersonFromDto_Service;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.CreatePersonManagement;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.IsEmailPresent_Service;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpPost <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private PersonsManagementRepository personsManagementRepository;
	private ModelMapper mapper;
	private AuthenticatedUsername authenticatedUsername;
	private PersonExceptions personException;
	private IsEmailPresent_Service isEmailPresentService;
	
	public PersonService_HttpPost(
			PersonRepository personRepository,
			PersonsManagementRepository personsManagementRepository,
			ModelMapper mapper,
			AuthenticatedUsername authenticatedUsername,
			PersonExceptions personException,
			IsEmailPresent_Service isEmailPresentService) {
		this.personRepository = personRepository;
		this.personsManagementRepository = personsManagementRepository;
		this.mapper = mapper;
		this.authenticatedUsername = authenticatedUsername;
		this.personException = personException;
		this.isEmailPresentService = isEmailPresentService;
	}

	@Transactional     
	@SuppressWarnings("hiding")
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> registerService(
				T personDto,	 
				UriComponentsBuilder uriComponentsBuilder) {
		PersonEntity person = getFromPerson(personDto);

		boolean emailAlreadyRegistered = isEmailPresentService.isEmailPresent(person.getEmail());
		personException.existingEmailException(emailAlreadyRegistered);

		PersonsManagementEntity personManagement = 
				createManagement(person);
//				createPersonManagement(person);

		personRepository.save(person);
		personsManagementRepository.save(personManagement);

		var uri = new PersonService_CreateUri().uriBuild(	uriComponentsBuilder, 
															person.getId(), 
															person.getIsNaturalPerson());

		var personListingDto =
				new PersonService_CreateDto<>(this.mapper).selectNaturalOrLegalPersonToListing_Dto(person); 

		return new DtoRecord_ServicePerson<>(uri, personListingDto);
	}

	protected PersonsManagementEntity createManagement(PersonEntity person) {
		return new CreatePersonManagement(this.authenticatedUsername).create(person, HttpVerbEnum.POST);
	}

	@SuppressWarnings("hiding")
	protected <T> PersonEntity getFromPerson(T personDto) {
		return new CreatePersonFromDto_Service(this.mapper).create(personDto);
	}

//	protected boolean isEmailAlreadyRegistered(PersonEntity person) {
//		String email = person.getEmail();
//		var existingEmail = personRepository.findByEmail(email);
//		boolean emailAlreadyRegistered = false;
//		if(existingEmail != null)
//			emailAlreadyRegistered = true;
//		return emailAlreadyRegistered;
//	}

//	protected PersonsManagementEntity createPersonManagement(PersonEntity person) {
//		var personManagement = new PersonsManagementEntity();
//		personManagement.setPerson(person);
//		personManagement.setHttpVerb(HttpVerbEnum.POST);
//		personManagement.setInitialDate(clockForNow());
//		personManagement.setLoginUser(authenticatedUsername.getAuthenticatedUsername());
//
//		return personManagement;
//	}
//
//	protected LocalDateTime clockForNow() {
//		return LocalDateTime.now();
//	}
	
}

