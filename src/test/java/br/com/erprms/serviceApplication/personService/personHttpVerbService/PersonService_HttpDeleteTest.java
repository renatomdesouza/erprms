package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import static br.com.erprms.testBuilders.Constants_Person.LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.LEGAL_PERSON_NOT_USED;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON_NOT_USED;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constant_UserLogged.USER_lOGGED;
import static br.com.erprms.testBuilders.Constant_LocalDateTimeNow.LOCAL_DATE_TIME_NOW;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson.DtoClass_LegalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson.DtoClass_NaturalPersonOfListing;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.infrastructure.localDateTime_Setter.LocalDateTime_Setter;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.IsEmailPresent_Service;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonService_HttpDeleteTest {
	@InjectMocks @Spy private PersonService_HttpDelete <PersonListingDto> personService_HttpDelete;
	@Spy private PersonExceptions personException;
	@Spy private ModelMapper mapper;
	@Mock private PersonRepository personRepository;
	@Mock private PersonsManagementRepository personsManagementRepository;
	@Mock private PersonEntity personEntity;
	@Mock private IsEmailPresent_Service isEmailPresentService;
	@Mock private AuthenticatedUsername authenticatedUsername;
	@Mock private LocalDateTime_Setter localDateTime_Setter;

	@ParameterizedTest
	@MethodSource("personsOfRegistry_NotUsed")
	@DisplayName("Should correct delete with person in not use")
	void unitTest_CorrectDeleteToSave(PersonEntity person) {
		when(personRepository.getReferenceById(person.getId())).thenReturn(person);

		var dtoRecord = personService_HttpDelete.excludeService(person.getId(), URI_COMPONENTS_BUILDER);

		verify(personRepository, times(1)).save(any());
		verify(personsManagementRepository, times(1)).save(any());
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePerson.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonListingDto.class));
		assertThat(dtoRecord.uri(), instanceOf(URI.class));
	}
	
	@ParameterizedTest
	@MethodSource("personsOfRegistry")
	@DisplayName("Should throw exception and not save with person in use")
	void unitTest_IncorrectDeleteToSave(PersonEntity person) {
		when(personRepository.getReferenceById(person.getId())).thenReturn(person);

		ResponseStatusException ex =
				Assertions.assertThrows(
						ResponseStatusException.class,
						() -> personService_HttpDelete.excludeService(person.getId(), URI_COMPONENTS_BUILDER));

		verify(personRepository, never()).save(any());
		verify(personsManagementRepository, never()).save(any());
		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"This person cannot be deleted because there is a qualification registered for them\"") );
	}

	@ParameterizedTest
	@MethodSource("naturalPersonsToDelete")
	@DisplayName("Should create a natural person from the natural person's Dto")
	<T> void unitTest_CorrectDeleteOfNaturalPerson(PersonEntity person) {
		when(personRepository.getReferenceById(person.getId())).thenReturn(person);

		var dtoRecord = personService_HttpDelete.excludeService(person.getId(), URI_COMPONENTS_BUILDER);

		assertThat(dtoRecord.dtoOfPerson(), instanceOf(DtoClass_NaturalPersonOfListing.class));
	}

	@ParameterizedTest
	@MethodSource("legalPersonsToDelete")
	@DisplayName("Should create a legal person from the natural person's Dto")
	<T> void unitTest_CorrectDeleteOfLegalPerson(PersonEntity person) {
		when(personRepository.getReferenceById(person.getId())).thenReturn(person);

		var dtoRecord = personService_HttpDelete.excludeService(person.getId(), URI_COMPONENTS_BUILDER);

		assertThat(dtoRecord.dtoOfPerson(), instanceOf(DtoClass_LegalPersonOfListing.class));
	}

	@ParameterizedTest
	@MethodSource("personsOfRegistry")
	@DisplayName("Should save managements records properly")
	<T> void unitTest_CorrectDeleteOfManagementPersons(PersonEntity personEntity) {
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(USER_lOGGED);
		when(localDateTime_Setter.nowSetter()).thenReturn(LOCAL_DATE_TIME_NOW);

		var personManagement = personService_HttpDelete.createManagement(personEntity);

		assertThat(personManagement.getPerson(), instanceOf(PersonEntity.class));
		assertThat(personManagement.getHttpVerb(), is(HttpVerbEnum.DELETE));
		assertThat(personManagement.getInitialDate(), instanceOf(LocalDateTime.class));
		assertThat(personManagement.getInitialDate(), is(LOCAL_DATE_TIME_NOW));
		assertThat(personManagement.getLoginUser(), is(USER_lOGGED));
	}

	
	static Stream<? extends Arguments> personsOfRegistry(){
		return Stream.of(
				Arguments.of(NATURAL_PERSON),
				Arguments.of(LEGAL_PERSON));
	}
	
	static Stream<? extends Arguments> personsOfRegistry_NotUsed(){
		return Stream.of(
				Arguments.of(NATURAL_PERSON_NOT_USED),
				Arguments.of(LEGAL_PERSON_NOT_USED));
	}
	
	static Stream<? extends Arguments> naturalPersonsToDelete(){
		return Stream.of(
				Arguments.of(NATURAL_PERSON_NOT_USED));
	}
	
	static Stream<? extends Arguments> legalPersonsToDelete(){
		return Stream.of(
				Arguments.of(LEGAL_PERSON_NOT_USED));
	}
}
