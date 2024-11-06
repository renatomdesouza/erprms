package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.ACCOUNTANT_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.CLIENT_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.FULL_TIME_EMPLOYEE_AND_MANAGER_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.PART_TIME_EMPLOYEE_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.PROVIDER_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.RESPONSIBLE_FOR_LEGAL_PERSON_DTO;
import static br.com.erprms.testBuilders.Constants_Person.LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.PERSON_QUALIFICATION_SUPERCLASS;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.testBuilders.Constants_QualificationClass;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonQualificationService_HttpPostTest {
	@InjectMocks @Spy private PersonQualificationService_HttpPost personQualificationService_HttpPost;
	@Spy private ModelMapper mapper;
	@Mock private PersonQualificationExceptions exceptionService;
	@Mock private PersonRepository personRepository;
	@Mock private PersonQualificationRepository personQualificationRepository;
	@Mock private AuthenticatedUsername authenticatedUsername;

	
	@ParameterizedTest
	@MethodSource("provideArguments_CorrectCreateToSave")
	@DisplayName("Should create correctly of qualification person")
	<T extends PersonQualificationInputDtoInterface> void unitTest_CorrectCreateToSave(
				PersonEntity person,
				String qualification,
				T dto,
				Class<PersonQualificationInputDtoInterface> qualificationClass,
				UriComponentsBuilder uriComponentsBuilder
		) throws ClassNotFoundException {
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn("any user");
		
		var dtoRecord = 
				personQualificationService_HttpPost
							.registerService(dto, uriComponentsBuilder, qualification);
	
		verify(personQualificationService_HttpPost, times(1)).mismatchBetweenQualifications(dto.getPerson_Id(), qualification);
		
		verify(personQualificationService_HttpPost, times(1)).personQualificationConfigure(any(), any());
		
		verify(mapper, times(1)).map(dto, qualificationClass);
		verify(personQualificationRepository, times(1)).save(any());
		
		assertThat(person.getStatusPersonEnum(), is(StatusPersonalUsedEnum.USED));
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePersonQualification.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonQualificationOutputDtoInterface.class));
		assertThat(dtoRecord.uri() , instanceOf(URI.class)); 
	}
	
	
	@ParameterizedTest
	@MethodSource("provideArguments_CorrectCreateToSave")
	@DisplayName("Should not create of qualification person - existent Id")
	<T extends PersonQualificationInputDtoInterface> void unitTest_IncorrectCreateToSave_ExistentId(
				PersonEntity person,  // not used
				String qualification, 
				T dto, 
				Class<PersonQualificationInputDtoInterface> qualificationClass, 
				UriComponentsBuilder uriComponentsBuilder 
		) throws ClassNotFoundException {
		when(personRepository.getReferenceById(anyLong())).thenReturn(null);// ==> This stub was only declared for readability - Mockito returns null automatically

		try {
			personQualificationService_HttpPost
								.registerService(dto, uriComponentsBuilder, qualification);
		} catch (ResponseStatusException ignored) { }
		
		ResponseStatusException ex = Assertions.assertThrows(
				ResponseStatusException.class,
				() -> personQualificationService_HttpPost
								.registerService(dto, uriComponentsBuilder, qualification));
		
		assertThat(ex.getMessage(), is(	"507 INSUFFICIENT_STORAGE \"There is no \"Person\" registered with this \"Id\"\"") );
		
		verify(personQualificationService_HttpPost, never()).mismatchBetweenQualifications(dto.getPerson_Id(), qualification);
		
		verify(personQualificationService_HttpPost, never()).personQualificationConfigure(any(), any());
		
		verify(mapper, never()).map(dto, qualificationClass);
		verify(personQualificationRepository, never()).save(any());
	}

	
	@ParameterizedTest
	@MethodSource("provideArguments_CorrectCreateToSave")
	@DisplayName("Should not create of qualification person - mismatch between qualifications")
	<T extends PersonQualificationInputDtoInterface> void unitTest_IncorrectCreateToSave_MismatchBetweenQualifications(
				PersonEntity person,  // not used
				String qualification, 
				T dto, 
				Class<PersonQualificationInputDtoInterface> qualificationClass, 
				UriComponentsBuilder uriComponentsBuilder 
		) throws ClassNotFoundException {
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		when(personQualificationService_HttpPost
				.mismatchBetweenQualifications(dto.getPerson_Id(), qualification)).thenReturn(true);
		
		try {
			personQualificationService_HttpPost
								.registerService(dto, uriComponentsBuilder, qualification);
		} catch (ResponseStatusException ignored) { }
		
		ResponseStatusException ex = Assertions.assertThrows(
				ResponseStatusException.class,
				() -> personQualificationService_HttpPost
								.registerService(dto, uriComponentsBuilder, qualification));
				
		assertThat(ex.getMessage(), is(	"507 INSUFFICIENT_STORAGE \"A person can only be a Manager, a regular Employee or a Part-Time employee, "
										+ "and still cannot have the same active qualification.\"") );
		
		verify(personQualificationService_HttpPost, times(2)).mismatchBetweenQualifications(dto.getPerson_Id(), qualification);
		
		verify(personQualificationService_HttpPost, never()).personQualificationConfigure(any(), any());
		
		verify(mapper, never()).map(dto, qualificationClass);
		verify(personQualificationRepository, never()).save(any());
	}
	
	@ParameterizedTest
	@MethodSource("personsOfRegistry")
	@DisplayName("Should create incorrectly of qualification person")
	void unitTest_IncorrectCreateToSave(PersonEntity person) {
		final var LOCAL_DATE_TIME_NOW = LocalDateTime.of(2024, 2, 3, 16, 30, 15);
		final var USER_lOGGED = "any user";
		
		when(personQualificationService_HttpPost.nowSetter()).thenReturn(LOCAL_DATE_TIME_NOW);
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(USER_lOGGED);
		
		var personQualification = 
				personQualificationService_HttpPost
					.personQualificationConfigure(person, PERSON_QUALIFICATION_SUPERCLASS);
		
		assertThat(personQualification.getIsActual(), is(true));
		assertThat(personQualification.getPerson(), is(person));
		assertThat(personQualification.getInitialDate(), is(LOCAL_DATE_TIME_NOW));
		assertThat(personQualification.getHttpVerb(), is(HttpVerbEnum.POST));
		assertThat(personQualification.getLoginUser(), is(USER_lOGGED));
	}
	
	
	static Stream<? extends Arguments> personsOfRegistry(){
		return Stream.of(
				Arguments.of(NATURAL_PERSON),
				Arguments.of(LEGAL_PERSON));
	}
	
	private static Stream<? extends Arguments> provideArguments_CorrectCreateToSave() throws ClassNotFoundException {
	         return Stream.of(
	        		// combinations of natural persons
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 MANAGER,
	                		 FULL_TIME_EMPLOYEE_AND_MANAGER_DTO,
	                		 Constants_QualificationClass.manager(), 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 FULL_TIME_EMPLOYEE,
	                		 FULL_TIME_EMPLOYEE_AND_MANAGER_DTO,
	                		 Constants_QualificationClass.fullTimeEmployee() , 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 PART_TIME_EMPLOYEE,
	                		 PART_TIME_EMPLOYEE_DTO,
	                		 Constants_QualificationClass.partTimeEmployee() , 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 ACCOUNTANT, 
	                		 ACCOUNTANT_DTO,
	                		 Constants_QualificationClass.accountant(),
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 CLIENT,
	                		 CLIENT_DTO,
	                		 Constants_QualificationClass.client(), 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 PROVIDER,
	                		 PROVIDER_DTO,
	                		 Constants_QualificationClass.provider(), 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 NATURAL_PERSON,
	                		 RESPONSIBLE_FOR_LEGAL_PERSON,
	                		 RESPONSIBLE_FOR_LEGAL_PERSON_DTO,
	                		 Constants_QualificationClass.responsibleForLegalPerson() , 
	                		 URI_COMPONENTS_BUILDER),
	              // combinations of legal persons
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 MANAGER,
	                		 FULL_TIME_EMPLOYEE_AND_MANAGER_DTO,
	                		 Constants_QualificationClass.manager(), 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 FULL_TIME_EMPLOYEE,
	                		 FULL_TIME_EMPLOYEE_AND_MANAGER_DTO,
	                		 Constants_QualificationClass.fullTimeEmployee() , 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 PART_TIME_EMPLOYEE,
	                		 PART_TIME_EMPLOYEE_DTO,
	                		 Constants_QualificationClass.partTimeEmployee() , 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 ACCOUNTANT, 
	                		 ACCOUNTANT_DTO,
	                		 Constants_QualificationClass.accountant(),
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 CLIENT,
	                		 CLIENT_DTO,
	                		 Constants_QualificationClass.client(), 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 PROVIDER,
	                		 PROVIDER_DTO,
	                		 Constants_QualificationClass.provider(), 
	                		 URI_COMPONENTS_BUILDER),
	                 Arguments.of(
	                		 LEGAL_PERSON,
	                		 RESPONSIBLE_FOR_LEGAL_PERSON,
	                		 RESPONSIBLE_FOR_LEGAL_PERSON_DTO,
	                		 Constants_QualificationClass.responsibleForLegalPerson() , 
	                		 URI_COMPONENTS_BUILDER)
	         );
	     };
}
