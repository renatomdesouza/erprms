package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.testBuilders.Constants_Person.DTO_RECORD_LEGAL_PERSON_OF_REGISTRY;
import static br.com.erprms.testBuilders.Constants_Person.DTO_RECORD_NATURAL_PERSON_OF_REGISTRY;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constants_PersonQualification.FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS;
import static br.com.erprms.testBuilders.Constants_PersonQualification.PERSON_QUALIFICATION_SUPERCLASS;
import static br.com.erprms.testBuilders.Constants_PersonQualification.PERSON_QUALIFICATION_SUPERCLASS_02;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPerson_Setter;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonQualificationService_HttpPostTest {
	@InjectMocks @Spy private PersonQualificationService_HttpPost personQualificationService_HttpPost;
	@Spy private ModelMapper mapper;
	@Mock private PersonQualificationExceptions exceptionService;
	@Mock private PersonRepository personRepository;
	@Mock private PersonQualificationRepository personQualificationRepository;
	@Mock private StatusPerson_Setter statusPerson;
	@Mock private AuthenticatedUsername authenticatedUsername;
	
	@Test
	void Test01() throws ClassNotFoundException {
		@SuppressWarnings("unchecked")
		final Class<PersonQualificationInputDtoInterface> MANAGER_PERSON_QUALIFICATION_CLASS = 
				(Class<PersonQualificationInputDtoInterface>) Class.forName(
						"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification");
		
		when(personRepository.getReferenceById(anyLong())).thenReturn(NATURAL_PERSON);
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn("any user");
		
		var dtoRecord = 
				personQualificationService_HttpPost
							.registerService(FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS, URI_COMPONENTS_BUILDER, MANAGER);

		verify(exceptionService, times(1)).exceptionForPersonWhoDoesNotExist_02(anyBoolean());
		verify(exceptionService, times(1)).mismatchExceptionBetweenQualifications_02(anyBoolean());
		
		verify(personQualificationService_HttpPost, times(1)).personQualificationConfigure(any(), any());
		
		verify(mapper, times(1)).map(FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS, MANAGER_PERSON_QUALIFICATION_CLASS);
		verify(personQualificationRepository, times(1)).save(any());
		
		assertThat(NATURAL_PERSON.getStatusPersonEnum(), is(StatusPersonalUsedEnum.USED));
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePersonQualification.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(OutputDtoClass_FullTimeEmployeeAndManager.class));
		assertThat(dtoRecord.uri() , instanceOf(URI.class)); 
	}
	
	@Test
	void Test02() {
		final var LOCAL_DATE_TIME_NOW = LocalDateTime.of(2024, 2, 3, 16, 30, 15);
		final var USER_lOGGED = "any user";
		
		when(personQualificationService_HttpPost.nowSetter()).thenReturn(LOCAL_DATE_TIME_NOW);
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(USER_lOGGED);
		
		var personQualification = 
				personQualificationService_HttpPost
					.personQualificationConfigure(NATURAL_PERSON, PERSON_QUALIFICATION_SUPERCLASS);
		
		assertThat(personQualification.getIsActual(), is(true));
		assertThat(personQualification.getPerson(), is(NATURAL_PERSON));
		assertThat(personQualification.getInitialDate(), is(LOCAL_DATE_TIME_NOW));
		assertThat(personQualification.getHttpVerb(), is(HttpVerbEnum.POST));
		assertThat(personQualification.getLoginUser(), is(USER_lOGGED));
	}

}
