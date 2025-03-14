package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.PersonsManagementEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.infrastructure.localDateTime_Setter.LocalDateTime_Setter;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.CreatePersonFromDto_Service;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.IsEmailPresent_Service;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.PersonManagement_Service;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpPost <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private PersonsManagementRepository personsManagementRepository;
	private ModelMapper mapper;
	private AuthenticatedUsername authenticatedUsername;
	private PersonExceptions personException;
	private IsEmailPresent_Service isEmailPresentService;
	private LocalDateTime_Setter localDateTime_Setter;
	
	
	public PersonService_HttpPost(
			PersonRepository personRepository,
			PersonsManagementRepository personsManagementRepository,
			ModelMapper mapper,
			AuthenticatedUsername authenticatedUsername,
			PersonExceptions personException,
			IsEmailPresent_Service isEmailPresentService,
			LocalDateTime_Setter localDateTime_Setter) {
		this.personRepository = personRepository;
		this.personsManagementRepository = personsManagementRepository;
		this.mapper = mapper;
		this.authenticatedUsername = authenticatedUsername;
		this.personException = personException;
		this.isEmailPresentService = isEmailPresentService;
		this.localDateTime_Setter = localDateTime_Setter;
	}

	@Transactional     
	@SuppressWarnings("hiding")
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> registerService(
				T personDto,	 
				UriComponentsBuilder uriComponentsBuilder) {
		PersonEntity person = getFromPerson(personDto);

		boolean emailAlreadyRegistered = isEmailPresentService.isEmailPresent(person.getEmail());
		personException.existingEmailException(emailAlreadyRegistered);

		PersonsManagementEntity personManagement = createManagement(person);

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
		return new PersonManagement_Service(this.authenticatedUsername, this.localDateTime_Setter).create(person, HttpVerbEnum.POST);
	}

	@SuppressWarnings("hiding")
	protected <T> PersonEntity getFromPerson(T personDto) {
		return new CreatePersonFromDto_Service(this.mapper).create(personDto);
	}
}

