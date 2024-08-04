package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import br.com.erprms.domainModel.personDomain.PersonsManagement_Entity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import br.com.erprms.infrastructure.getAuthentication.AuthenticationFacade;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonService_HttpPut <T extends PersonListingDto> {
	private PersonRepository personRepository;
	private PersonsManagementRepository personsManagementRepository;
	private ModelMapper mapper;
	private final AuthenticationFacade authenticationFacade;
	private final PersonExceptions personException;

	public PersonService_HttpPut(
			PersonRepository personRepository,
			ModelMapper mapper,
			PersonsManagementRepository personsManagementRepository,
			AuthenticationFacade authenticationFacade,
			PersonExceptions personException) {
		this.personRepository = personRepository;
		this.personsManagementRepository = personsManagementRepository;
		this.mapper = mapper;
		this.authenticationFacade = authenticationFacade;
		this.personException = personException;
	}
	
	@Transactional
	@SuppressWarnings("hiding")
	public <T> DtoRecord_ServicePerson<? extends PersonListingDto> updateService(
			T personDtoOfRecord,
			@NonNull Long id_person,
			UriComponentsBuilder uriComponentsBuilder) {
		
		var person = personRepository.getReferenceById(id_person);

		updatePerson(person, personDtoOfRecord);
		var personManagement = setPersonsManagement(person);

		personRepository.save(person);
		personsManagementRepository.save(personManagement);

		var uri = new PersonService_CreateUri().uriBuild(
							uriComponentsBuilder, 
							person.getId(), 
							person.getIsNaturalPerson());

		var personListingDto =  new PersonService_CreateDto<>(mapper)
										.selectNaturalOrLegalPersonToListing_Dto(person); 
		
		return new DtoRecord_ServicePerson<>(uri, personListingDto);
	}

	private PersonsManagement_Entity setPersonsManagement(PersonEntity person) {
		var personManagement = new PersonsManagement_Entity();
		personManagement.setPerson(person);
		personManagement.setHttpVerb(HttpVerbEnum.PUT);
		personManagement.setInitialDate(LocalDateTime.now());
		personManagement.setLoginUser(authenticationFacade.getAuthentication());

		return personManagement;
	}

	@SuppressWarnings("hiding")
	public <T> void updatePerson(PersonEntity person, T personDtoOfRecord) {

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
