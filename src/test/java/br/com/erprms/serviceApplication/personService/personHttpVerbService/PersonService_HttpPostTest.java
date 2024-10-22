package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import static br.com.erprms.testBuilders.Constants_Dto.DTO_RECORD_LEGAL_PERSON_OF_REGISTRY;
import static br.com.erprms.testBuilders.Constants_Dto.DTO_RECORD_NATURAL_PERSON_OF_REGISTRY;
import static br.com.erprms.testBuilders.Constants_Dto.IS_EMAIL_TRUE;
import static br.com.erprms.testBuilders.Constants_Dto.URI_COMPONENTS_BUILDER;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.CreatePersonManagement;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices.IsEmailPresent_Service;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonService_HttpPostTest {
	@InjectMocks @Spy private PersonService_HttpPost<PersonListingDto> personServiceHttpPost;
	@Spy private PersonExceptions personException;
	@Spy private ModelMapper mapper;
	@Mock private IsEmailPresent_Service isEmailPresentService;
	@Mock private PersonRepository personRepository;
	@Mock private PersonsManagementRepository personsManagementRepository;
	@Mock private CreatePersonManagement createPersonManagement;
	@Mock private AuthenticatedUsername authenticatedUsername;
	@Mock private PersonEntity personEntity;

	@ParameterizedTest
	@MethodSource("dtoRecords_PersonsOfRegistry")
	@DisplayName("Should throw exception and not save with already existing email")
	<T> void unitTest_IncorrectPostToSave(T personDto) {
		when(isEmailPresentService.isEmailPresent(anyString())).thenReturn(IS_EMAIL_TRUE);
		
		try {
			personServiceHttpPost.registerService(personDto, URI_COMPONENTS_BUILDER);
		} catch (ResponseStatusException ignored) { }
		
		ResponseStatusException ex =
				Assertions.assertThrows(
						ResponseStatusException.class,
						() -> personServiceHttpPost.registerService(personDto, URI_COMPONENTS_BUILDER));
		
		verify(personRepository, never()).save(any());
		verify(personsManagementRepository, never()).save(any());
		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"The user's email is already registered in the system - They cannot be duplicated or altered\"") );
	}
	
	@ParameterizedTest
	@MethodSource("dtoRecords_PersonsOfRegistry")
	@DisplayName("Should save person, save management record and return output dto to user") 
	<T> void unitTest_CorrectPostToSave(T personDto) {	
		var dtoRecord = personServiceHttpPost.registerService(personDto, URI_COMPONENTS_BUILDER);
		
		verify(personRepository, times(1)).save(any());
		verify(personsManagementRepository, times(1)).save(any());
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePerson.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonListingDto.class));
		assertThat(dtoRecord.uri(), instanceOf(URI.class));	
	}

	@ParameterizedTest
	@MethodSource("dtoRecord_NaturalPersonOfRegistry")
	@DisplayName("Should create a natural person from the natural person's Dto")
	<T> void unitTest_CorrectCreateOfNaturalPerson(T personDto) {
		var person = personServiceHttpPost.getFromPerson(personDto);

		assertThat(personDto, instanceOf(DtoRecord_NaturalPersonOfRegistry.class));
		assertThat(person, instanceOf(PersonEntity.class));
		assertNotNull(person);
	}

	@ParameterizedTest
	@MethodSource("dtoRecord_LegalPersonOfRegistry")
	@DisplayName("Should create a legal person from the legal person's Dto")
	<T> void unitTest_CorrectCreateOfLegalPerson(T personDto) {
		var person = personServiceHttpPost.getFromPerson(personDto);

		assertThat(personDto, instanceOf(DtoRecord_LegalPersonOfRegistry.class));
		assertThat(person, instanceOf(PersonEntity.class));
		assertNotNull(person);
	}

//	@ParameterizedTest
//	@MethodSource("dtoRecords_PersonsOfRegistry")
//	@DisplayName("Should save persons properly")
//	<T> void unitTest_CorrectSaveOfPersons(T personDto) {
//		personServiceHttpPost.registerService(personDto, URI_COMPONENTS_BUILDER);
//
//		verify(personRepository, times(1)).save(any());
//		verify(personsManagementRepository, times(1)).save(any());
//	}

//	@ParameterizedTest
//	@MethodSource("dtoRecords_PersonsOfRegistry")
//	@DisplayName("Should save managements records properly")
//	<T> void unitTest_CorrectSaveOfManagementPersons(T personDto) {
//		String anyAuthenticatedUser = "AnyUser";
//		LocalDateTime nowSimulator = LocalDateTime.of(2024, 12, 31, 4, 30, 15);
//		
//		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(anyAuthenticatedUser);
//		when(personServiceHttpPost.clockForNow()).thenReturn(nowSimulator);
//
//		var person = personServiceHttpPost.getFromPerson(personDto);
//		var personManagement = personServiceHttpPost.createPersonManagement(person);
//
//		assertThat(personManagement.getPerson(), instanceOf(PersonEntity.class));
//		assertThat(personManagement.getHttpVerb(), is(HttpVerbEnum.POST));
//		assertThat(personManagement.getInitialDate(), instanceOf(LocalDateTime.class));
//		assertThat(personManagement.getInitialDate(), is(nowSimulator));
//		assertThat(personManagement.getLoginUser(), is(anyAuthenticatedUser));
//	}
	
	@ParameterizedTest
	@MethodSource("dtoRecords_PersonsOfRegistry")
	@DisplayName("Should save managements records properly")
	<T> void unitTest_CorrectSaveOfManagementPersons(T personDto) {
		String anyAuthenticatedUser = "AnyUser";
//		LocalDateTime nowSimulator = LocalDateTime.of(2024, 12, 31, 4, 30, 15);
		
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(anyAuthenticatedUser);
//		when(createPersonManagement.clockForNow()).thenReturn(nowSimulator);

		var person = personServiceHttpPost.getFromPerson(personDto);
		var personManagement = personServiceHttpPost.createManagement(person);

		assertThat(personManagement.getPerson(), instanceOf(PersonEntity.class));
		assertThat(personManagement.getHttpVerb(), is(HttpVerbEnum.POST));
		assertThat(personManagement.getInitialDate(), instanceOf(LocalDateTime.class));
//		assertThat(personManagement.getInitialDate(), is(nowSimulator));
		assertThat(personManagement.getLoginUser(), is(anyAuthenticatedUser));
	}
	
	static Stream<? extends Arguments> dtoRecord_NaturalPersonOfRegistry(){
		return Stream.of(
				Arguments.of(DTO_RECORD_NATURAL_PERSON_OF_REGISTRY));
	}

	static Stream<? extends Arguments> dtoRecord_LegalPersonOfRegistry(){
		return Stream.of(
				Arguments.of(DTO_RECORD_LEGAL_PERSON_OF_REGISTRY));
	}

	static Stream<? extends Arguments> dtoRecords_PersonsOfRegistry(){
		return Stream.of(
				Arguments.of(DTO_RECORD_NATURAL_PERSON_OF_REGISTRY),
				Arguments.of(DTO_RECORD_LEGAL_PERSON_OF_REGISTRY));
	}
}
