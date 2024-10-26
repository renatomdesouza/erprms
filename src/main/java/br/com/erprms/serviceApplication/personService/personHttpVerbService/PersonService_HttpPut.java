package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
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
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.IsEmailPresent_Service;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.PersonManagement_Service;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.UpdatePersonFromDto_Service;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpPut <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private PersonsManagementRepository personsManagementRepository;
	private ModelMapper mapper;
	private AuthenticatedUsername authenticationFacade;
	private PersonExceptions personException;
	private IsEmailPresent_Service isEmailPresentService;

	public PersonService_HttpPut(
			PersonRepository personRepository,
			ModelMapper mapper,
			PersonsManagementRepository personsManagementRepository,
			AuthenticatedUsername authenticationFacade,
			PersonExceptions personException,
			IsEmailPresent_Service isEmailPresentService) {
		this.personRepository = personRepository;
		this.personsManagementRepository = personsManagementRepository;
		this.mapper = mapper;
		this.authenticationFacade = authenticationFacade;
		this.personException = personException;
		this.isEmailPresentService = isEmailPresentService;
	}
	
	@Transactional
	@SuppressWarnings("hiding")
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> updateService(
			T personDtoOfRecord,
			@NonNull String id_person,
			String email,
			UriComponentsBuilder uriComponentsBuilder) {
		PersonEntity person = personRepository.getReferenceById(Long.parseLong(id_person));
		
		boolean emailAlreadyRegistered = isEmailPresentService.isEmailPresent(person.getEmail(), email);
		personException.existingEmailException(emailAlreadyRegistered);	
		
		person = updatePersonFromDto(personDtoOfRecord, person);

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
		return new PersonManagement_Service(this.authenticationFacade).create(person, HttpVerbEnum.PUT);
	}

	@SuppressWarnings("hiding")
	protected <T> PersonEntity updatePersonFromDto(T personDtoOfRecord, PersonEntity person) {
		return new UpdatePersonFromDto_Service(this.mapper).update(personDtoOfRecord, person);
	}
	
//	protected PersonsManagementEntity createPersonManagement(PersonEntity person) {
//		var personManagement = new PersonsManagementEntity();
//		personManagement.setPerson(person);
//		personManagement.setHttpVerb(HttpVerbEnum.PUT);
//		personManagement.setInitialDate(clockForNow());
//		personManagement.setLoginUser(authenticationFacade.getAuthenticatedUsername());
//
//		return personManagement;
//	}
//	
//	protected LocalDateTime clockForNow() {
//		return LocalDateTime.now();
//	}

}
