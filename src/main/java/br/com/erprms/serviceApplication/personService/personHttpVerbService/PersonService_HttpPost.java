package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.net.URI;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.PersonsManagement_Entity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson.DtoClass_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson.DtoClass_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticationFacade;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService_HttpPost <T extends PersonListingDto> {
	private final PersonRepository personRepository;
	private final PersonsManagementRepository personsManagementRepository;
	private final ModelMapper mapper;
	private final AuthenticationFacade authenticationFacade;
	private final PersonExceptions personException;

	public PersonService_HttpPost(
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
	@SuppressWarnings({ "hiding", "null" })
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> registerService(
				T personDto,
				UriComponentsBuilder uriComponentsBuilder) {
		PersonEntity person = createPerson(personDto);

		String existingEmail = personRepository.findEmail(person.getEmail());
		personException.existingEmailException(existingEmail);

		var personManagement = setPersonsManagement(person);

		personRepository.save(person);
		personsManagementRepository.save(personManagement);

		URI uri = new PersonService_CreateUri().uriBuild(
							uriComponentsBuilder, 
							person.getId(), 
							person.getIsNaturalPerson());

		PersonListingDto personListingDto =
				new PersonService_CreateDto<>(mapper).selectNaturalOrLegalPersonToListing_Dto(person);

		return new DtoRecord_ServicePerson<>(uri, personListingDto);
	}

	private PersonsManagement_Entity setPersonsManagement(PersonEntity person) {
		PersonsManagement_Entity personManagement = new PersonsManagement_Entity();
		personManagement.setPerson(person);
		personManagement.setHttpVerb(HttpVerbEnum.POST);
		personManagement.setInitialDate(LocalDateTime.now());
		personManagement.setLoginUser(authenticationFacade.getAuthentication());

		return personManagement;
	}

	@SuppressWarnings("hiding")
	public <T> PersonEntity createPerson(T personDto) {

		PersonEntity person = new PersonEntity();

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

