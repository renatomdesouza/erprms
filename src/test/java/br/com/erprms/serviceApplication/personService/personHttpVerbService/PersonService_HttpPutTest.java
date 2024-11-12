package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import static br.com.erprms.testBuilders.Constants_Person.DTO_RECORD_LEGAL_PERSON_OF_REGISTRY;
import static br.com.erprms.testBuilders.Constants_Person.DTO_RECORD_NATURAL_PERSON_OF_REGISTRY;
import static br.com.erprms.testBuilders.Constants_Person.IS_EMAIL_TRUE;
import static br.com.erprms.testBuilders.Constants_Person.LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constant_UserLogged.USER_lOGGED;
import static br.com.erprms.testBuilders.Constant_LocalDateTimeNow.LOCAL_DATE_TIME_NOW;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.infrastructure.localDateTime_Setter.LocalDateTime_Setter;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.IsEmailPresent_Service;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonService_HttpPutTest {
	@InjectMocks @Spy private PersonService_HttpPut<PersonListingDto> personService_HttpPut;
	@Spy private PersonExceptions personException;
	@Spy private ModelMapper mapper;
	@Mock private PersonRepository personRepository;
	@Mock private PersonsManagementRepository personsManagementRepository;
	@Mock private PersonEntity personEntity;
	@Mock private IsEmailPresent_Service isEmailPresentService;
	@Mock private AuthenticatedUsername authenticatedUsername;
	@Mock private LocalDateTime_Setter localDateTime_Setter;

	@ParameterizedTest
	@MethodSource("personsOfRegistry")
	@DisplayName("Should save person, save management record and return output dto to user") 
	void unitTest_CorrectPutToSave(PersonEntity person) {	
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		
		var dtoRecord = personService_HttpPut.updateService(	Object.class, 
																String.valueOf(person.getId()), 
																person.getEmail(),
																URI_COMPONENTS_BUILDER);

		verify(personRepository, times(1)).save(any());
		verify(personsManagementRepository, times(1)).save(any());
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePerson.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonListingDto.class));
		assertThat(dtoRecord.uri(), instanceOf(URI.class));	
	}
	
	@ParameterizedTest
	@MethodSource("personsOfRegistry")
	@DisplayName("Should throw exception and not save with already existing email") 
	void unitTest_IncorrectPutToSave(PersonEntity person) {	
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		when(isEmailPresentService.isEmailPresent(anyString(), anyString())).thenReturn(IS_EMAIL_TRUE);
		
		ResponseStatusException ex =
				Assertions.assertThrows(
						ResponseStatusException.class,
						() -> personService_HttpPut.updateService(	Object.class, 
																	String.valueOf(person.getId()), 
																	person.getEmail(),
																	URI_COMPONENTS_BUILDER));

		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"The user's email is already registered in the system - They cannot be duplicated or altered\"") );

		verify(personRepository, times(1)).getReferenceById(anyLong());
		verify(personRepository, never()).save(any());
		verify(personsManagementRepository, never()).save(any());
	}
		
	@ParameterizedTest
	@MethodSource("naturalPerson_DtoAndPerson")
	@DisplayName("Should create a natural person from the natural person's Dto")
	<T> void unitTest_CorrectCreateOfNaturalPerson(T personDto, PersonEntity personEntity) {
		var person = personService_HttpPut.updatePersonFromDto(personDto, personEntity);

		assertThat(personDto, instanceOf(DtoRecord_NaturalPersonOfRegistry.class));
		assertThat(person, instanceOf(PersonEntity.class));
		assertNotNull(person);
	}

	@ParameterizedTest
	@MethodSource("legalPerson_DtoAndPerson")
	@DisplayName("Should create a legal person from the natural person's Dto")
	<T> void unitTest_CorrectCreateOfLegalPerson(T personDto, PersonEntity personEntity) {
		var person = personService_HttpPut.updatePersonFromDto(personDto, personEntity);

		assertThat(personDto, instanceOf(DtoRecord_LegalPersonOfRegistry.class));
		assertThat(person, instanceOf(PersonEntity.class));
		assertNotNull(person);
	}
	
	@ParameterizedTest
	@MethodSource("personsOfRegistry")
	@DisplayName("Should save managements records properly")
	<T> void unitTest_CorrectSaveOfManagementPersons(PersonEntity personEntity) {
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(USER_lOGGED);
		when(localDateTime_Setter.nowSetter()).thenReturn(LOCAL_DATE_TIME_NOW);

		var personManagement = personService_HttpPut.createManagement(personEntity);

		assertThat(personManagement.getPerson(), instanceOf(PersonEntity.class));
		assertThat(personManagement.getHttpVerb(), is(HttpVerbEnum.PUT));
		assertThat(personManagement.getInitialDate(), instanceOf(LocalDateTime.class));
		assertThat(personManagement.getInitialDate(), is(LOCAL_DATE_TIME_NOW));
		assertThat(personManagement.getLoginUser(), is(USER_lOGGED));
	}
	
	static Stream<? extends Arguments> personsOfRegistry(){
		return Stream.of(
				Arguments.of(NATURAL_PERSON),
				Arguments.of(LEGAL_PERSON));
	}
		
	static Stream<? extends Arguments> naturalPerson_DtoAndPerson(){
		return Stream.of(
				Arguments.of(DTO_RECORD_NATURAL_PERSON_OF_REGISTRY, NATURAL_PERSON));
	}
	
	static Stream<? extends Arguments> legalPerson_DtoAndPerson(){
		return Stream.of(
				Arguments.of(DTO_RECORD_LEGAL_PERSON_OF_REGISTRY, LEGAL_PERSON));
	}
}
