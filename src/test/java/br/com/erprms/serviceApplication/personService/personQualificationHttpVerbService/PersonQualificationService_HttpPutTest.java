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
import static br.com.erprms.testBuilders.Constants_PersonQualifications.ACCOUNTANT_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.CLIENT_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.MANAGER_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.PART_TIME_EMPLOYEE_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.PROVIDER_PERSON_QUALIFICATION;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.stream.Stream;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.AccountantRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.testBuilders.Constants_QualificationClass;

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
		verify(personRepository, times(1)).existsById(dto.getPerson_Id());
		
		verify(personQualificationService_HttpPut, times(1)).findOldPersonQuailfication(person, qualification);
		
		verify(personQualificationService_HttpPut, times(1)).updateSelected(any(), any(), any(), any());
		
		verify(personQualificationRepository, times(2)).save(any());
		
		assertThat(person.getStatusPersonEnum(), is(StatusPersonalUsedEnum.USED));
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePersonQualification.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonQualificationOutputDtoInterface.class));
		assertThat(dtoRecord.uri() , instanceOf(URI.class));
		
	}

	static Stream<? extends Arguments> updatesQualifications() throws ClassNotFoundException{
		return Stream.of(
				// combinations of natural persons
				Arguments.of(
						NATURAL_PERSON, 
						MANAGER,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						MANAGER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						FULL_TIME_EMPLOYEE,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						PART_TIME_EMPLOYEE,
						PART_TIME_EMPLOYEE_DTO, 
						PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						CLIENT,
						CLIENT_DTO, 
						CLIENT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						PROVIDER,
						PROVIDER_DTO, 
						PROVIDER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						RESPONSIBLE_FOR_LEGAL_PERSON,
						RESPONSIBLE_FOR_LEGAL_PERSON_DTO, 
						RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						NATURAL_PERSON, 
						ACCOUNTANT,
						ACCOUNTANT_DTO, 
						ACCOUNTANT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				// combinations of legal persons
				Arguments.of(
						LEGAL_PERSON, 
						MANAGER,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						MANAGER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						FULL_TIME_EMPLOYEE,
						FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, 
						FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						PART_TIME_EMPLOYEE,
						PART_TIME_EMPLOYEE_DTO, 
						PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						CLIENT,
						CLIENT_DTO, 
						CLIENT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						PROVIDER,
						PROVIDER_DTO, 
						PROVIDER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						RESPONSIBLE_FOR_LEGAL_PERSON,
						RESPONSIBLE_FOR_LEGAL_PERSON_DTO, 
						RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						LEGAL_PERSON, 
						ACCOUNTANT,
						ACCOUNTANT_DTO, 
						ACCOUNTANT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER)	
					);
	}
}
