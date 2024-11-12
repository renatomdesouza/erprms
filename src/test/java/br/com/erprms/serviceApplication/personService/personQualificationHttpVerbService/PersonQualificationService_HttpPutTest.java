package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constant_LocalDateTimeNow.LOCAL_DATE_TIME_NOW;
import static br.com.erprms.testBuilders.Constant_UserLogged.USER_lOGGED;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.ACCOUNTANT_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.CLIENT_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.FULL_TIME_EMPLOYEE_AND_MANAGER_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.PART_TIME_EMPLOYEE_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.PROVIDER_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.RESPONSIBLE_FOR_LEGAL_PERSON_DTO;
import static br.com.erprms.testBuilders.Constants_Person.LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_ACCOUNTANT_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_CLIENT_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_MANAGER_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_PROVIDER_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.NEW_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_ACCOUNTANT_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_CLIENT_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_MANAGER_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_PROVIDER_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;
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
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.infrastructure.localDateTime_Setter.LocalDateTime_Setter;
import br.com.erprms.repositoryAdapter.personRepository.AccountantRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonQualificationService_HttpPutTest {
	@InjectMocks @Spy private PersonQualificationService_HttpPut personQualificationService_HttpPut;
	@Spy private ModelMapper mapper;
	@Mock private PersonQualificationExceptions exceptionService;
	@Mock private PersonRepository personRepository;							
	@Mock private PersonQualificationRepository personQualificationRepository;
	@Mock private ManagerRepository managerRepository;	
	@Mock private AccountantRepository accountantRepository;
	@Mock private AuthenticatedUsername authenticatedUsername;
	@Mock private LocalDateTime_Setter localDateTime_Setter;

	@ParameterizedTest
	@MethodSource("updatesQualifications")
	@DisplayName("Should update correctly of qualification person")
	<T extends PersonQualificationSuperclassEntity, U extends PersonQualificationInputDtoInterface>  
	void unitTest_CorrectUpdateToSave(
			PersonEntity person,
			String qualification,
			U dto,
			T personQualification,
			UriComponentsBuilder uriComponentsBuilder) {
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		when(personQualificationService_HttpPut.findOldPersonQuailfication(any(), anyString())).thenReturn(personQualification);
		
		var dtoRecord = 
				personQualificationService_HttpPut.update(dto, uriComponentsBuilder, qualification);
		
		verify(personRepository, times(1)).getReferenceById(dto.getPerson_Id());
		verify(personQualificationService_HttpPut, times(1)).findOldPersonQuailfication(person, qualification);
		verify(personQualificationService_HttpPut, times(1)).updateSelected(any(), any(), any(), any());
		verify(personQualificationRepository, times(1)).saveAll(any());
		
		assertThat(person.getStatusPersonEnum(), is(StatusPersonalUsedEnum.USED));
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePersonQualification.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonQualificationOutputDtoInterface.class));
		assertThat(dtoRecord.uri() , instanceOf(URI.class));
	}
	
	@ParameterizedTest
	@MethodSource("updatesQualifications")
	@DisplayName("Should update incorrectly of qualification person - without person")
	<T extends PersonQualificationSuperclassEntity, U extends PersonQualificationInputDtoInterface>  
	void unitTest_IncorrecttUpdateToSave_withoutPerson(
			PersonEntity person,
			String qualification,
			U dto,
			T personQualification,
			UriComponentsBuilder uriComponentsBuilder) {
		when(personRepository.getReferenceById(anyLong())).thenReturn(null);// ==> This stub was only declared for readability - Mockito returns null automatically
		
		ResponseStatusException ex = Assertions.assertThrows(
				ResponseStatusException.class,
				() -> personQualificationService_HttpPut.update(dto, uriComponentsBuilder, qualification));
		
		assertThat(ex.getMessage(), is(	"507 INSUFFICIENT_STORAGE \"There is no \"Person\" registered with this \"Id\"\"") );
		
		verify(personRepository, times(1)).getReferenceById(dto.getPerson_Id());
		verify(personQualificationService_HttpPut, never()).findOldPersonQuailfication(person, qualification);
		verify(personQualificationService_HttpPut, never()).updateSelected(any(), any(), any(), any());
		verify(personQualificationRepository, never()).saveAll(any());
	}
	
	@ParameterizedTest
	@MethodSource("updatesQualifications")
	@DisplayName("Should update incorrectly of qualification person - without qualification")
	<T extends PersonQualificationSuperclassEntity, U extends PersonQualificationInputDtoInterface>  
	void unitTest_IncorrecttUpdateToSave_WithoutQualification(
			PersonEntity person,
			String qualification,
			U dto,
			T personQualification, // not used
			UriComponentsBuilder uriComponentsBuilder) {
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		when(personQualificationService_HttpPut.findOldPersonQuailfication(any(), anyString())).thenReturn(null);// ==> This stub was only declared for readability - Mockito returns null automatically
		
		ResponseStatusException ex = Assertions.assertThrows(
				ResponseStatusException.class,
				() -> personQualificationService_HttpPut.update(dto, uriComponentsBuilder, qualification));
		
		assertThat(ex.getMessage(), is(	"507 INSUFFICIENT_STORAGE \"This person does not have this qualification in the database\"") );
		assertThat(person.getStatusPersonEnum(), is(StatusPersonalUsedEnum.USED));
		
		verify(personRepository, times(1)).getReferenceById(dto.getPerson_Id());
		verify(personQualificationService_HttpPut, times(1)).findOldPersonQuailfication(person, qualification);
		verify(personQualificationService_HttpPut, never()).updateSelected(any(), any(), any(), any());
		verify(personQualificationRepository, never()).saveAll(any());
	}

	@ParameterizedTest
	@MethodSource("personsQualificationsOfRegistry")
	@DisplayName("Should correct configure and save qualification person")
	<T extends PersonQualificationSuperclassEntity> 
	void unitTest_CorrectConfigureAndSave(
			T oldPersonQualification,
			T newPersonQualification) {
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn(USER_lOGGED);
		when(localDateTime_Setter.nowSetter()).thenReturn(LOCAL_DATE_TIME_NOW);
		
		List<PersonQualificationSuperclassEntity> qualifications = 
				personQualificationService_HttpPut
					.personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

		verify(personQualificationRepository, times(1)).saveAll(any());
		
		assertThat(qualifications.get(0).getIsActual() , is(false));
		assertThat(qualifications.get(1).getIsActual() , is(true));

		assertThat(qualifications.get(1).getInitialDate(), is(LOCAL_DATE_TIME_NOW));
		assertThat(qualifications.get(1).getHttpVerb(), is(HttpVerbEnum.PUT));
		assertThat(qualifications.get(1).getLoginUser(), is(USER_lOGGED));
	}
	
	static Stream<? extends Arguments> personsQualificationsOfRegistry(){
		return Stream.of(
				Arguments.of(	
						OLD_MANAGER_PERSON_QUALIFICATION, 
						NEW_MANAGER_PERSON_QUALIFICATION),
				Arguments.of(
						OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						NEW_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION),
				Arguments.of(
						OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						NEW_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION),
				Arguments.of(
						OLD_CLIENT_PERSON_QUALIFICATION, 
						NEW_CLIENT_PERSON_QUALIFICATION),
				Arguments.of(
						OLD_PROVIDER_PERSON_QUALIFICATION, 
						NEW_PROVIDER_PERSON_QUALIFICATION),
				Arguments.of(
						OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						NEW_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION),
				Arguments.of(
						OLD_ACCOUNTANT_PERSON_QUALIFICATION, 
						NEW_ACCOUNTANT_PERSON_QUALIFICATION));
	}
	
	static Stream<? extends Arguments> updatesQualifications() throws ClassNotFoundException{
		return Stream.of(
				// combinations of natural persons
				Arguments.of(
						NATURAL_PERSON, 
						MANAGER,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						OLD_MANAGER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						FULL_TIME_EMPLOYEE,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						PART_TIME_EMPLOYEE,
						PART_TIME_EMPLOYEE_DTO, 
						OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						CLIENT,
						CLIENT_DTO, 
						OLD_CLIENT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						PROVIDER,
						PROVIDER_DTO, 
						OLD_PROVIDER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						RESPONSIBLE_FOR_LEGAL_PERSON,
						RESPONSIBLE_FOR_LEGAL_PERSON_DTO, 
						OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						ACCOUNTANT,
						ACCOUNTANT_DTO, 
						OLD_ACCOUNTANT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				// combinations of legal persons
				Arguments.of(
						LEGAL_PERSON, 
						MANAGER,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						OLD_MANAGER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						FULL_TIME_EMPLOYEE,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						PART_TIME_EMPLOYEE,
						PART_TIME_EMPLOYEE_DTO, 
						OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						CLIENT,
						CLIENT_DTO, 
						OLD_CLIENT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						PROVIDER,
						PROVIDER_DTO, 
						OLD_PROVIDER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						RESPONSIBLE_FOR_LEGAL_PERSON,
						RESPONSIBLE_FOR_LEGAL_PERSON_DTO, 
						OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						ACCOUNTANT,
						ACCOUNTANT_DTO, 
						OLD_ACCOUNTANT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER)	
					);
	}
}
