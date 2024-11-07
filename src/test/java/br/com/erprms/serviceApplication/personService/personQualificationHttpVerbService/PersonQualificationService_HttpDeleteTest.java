package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.ACCOUNTANT_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.CLIENT_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.FULL_TIME_EMPLOYEE_AND_MANAGER_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.PART_TIME_EMPLOYEE_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.PROVIDER_DTO;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.RESPONSIBLE_FOR_LEGAL_PERSON_DTO;
import static br.com.erprms.testBuilders.Constants_Person.LEGAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ClientPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.AccountantRepository;
import br.com.erprms.repositoryAdapter.personRepository.ClientRepository;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PartTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.ProviderRepository;
import br.com.erprms.repositoryAdapter.personRepository.ResponsibleForLegalPersonRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonQualificationService_HttpDeleteTest {
	@InjectMocks @Spy private PersonQualificationService_HttpDelete personQualificationService_HttpDelete;
	@Spy private ModelMapper mapper;
	@Mock private PersonQualificationExceptions exceptionService;
	@Mock private PersonRepository personRepository;							
	@Mock private PersonQualificationRepository personQualificationRepository;
	@Mock private ManagerRepository managerRepository;
	@Mock private FullTimeEmployeeRepository fullTimeEmployeeRepository;
	@Mock private PartTimeEmployeeRepository partTimeEmployeeRepository;
	@Mock private ClientRepository clientRepository;
	@Mock private ProviderRepository providerRepository;
	@Mock private ResponsibleForLegalPersonRepository responsibleForLegalPersonRepository;
	@Mock private AccountantRepository accountantRepository;
	@Mock private AuthenticatedUsername authenticatedUsername;
	
	@ParameterizedTest
	@MethodSource("excludesQualifications")
	@DisplayName("Should update correctly of qualification person")
	<T extends PersonQualificationSuperclassEntity, U extends PersonQualificationInputDtoInterface> 
	void unitTest_CorrectExcludeToSave(
			Long person_Id,
			PersonEntity person,
			String qualification,
			T personQualification,
			UriComponentsBuilder uriComponentsBuilder) {
		when(personRepository.getReferenceById(anyLong())).thenReturn(person);
		
		switch (qualification) {
			case MANAGER -> {
				when(managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((ManagerPersonQualification) personQualification); break; }
			case FULL_TIME_EMPLOYEE -> {
				when(fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((FullTimeEmployeePersonQualification) personQualification); break; }
			case PART_TIME_EMPLOYEE -> {
				when(partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((PartTimeEmployeePersonQualification) personQualification); break; }
			case ACCOUNTANT -> {
				when(accountantRepository.findAccountantPersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((AccountantPersonQualification) personQualification); break; }
			case CLIENT -> {
				when(clientRepository.findClientPersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((ClientPersonQualification) personQualification); break; }
			case PROVIDER -> {
				when(providerRepository.findProviderPersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((ProviderPersonQualification) personQualification); break; }
			case RESPONSIBLE_FOR_LEGAL_PERSON -> {
				when(responsibleForLegalPersonRepository.findResponsibleForLegalPersonQualificationByIsActualIsTrueAndPerson(person))
						.thenReturn((ResponsibleForLegalPersonQualification) personQualification); break; }}
		
		var dtoRecord = 
				personQualificationService_HttpDelete.exclude(person_Id, uriComponentsBuilder, qualification);
		
		verify(personRepository, times(1)).getReferenceById(person_Id);
		verify(personQualificationService_HttpDelete, times(1)).personsQualifications_ConfigureAndSave(any(), any());
		verify(personQualificationRepository, times(1)).saveAll(any());

		assertThat(person.getStatusPersonEnum(), is(StatusPersonalUsedEnum.NOT_USED));
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePersonQualification.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(PersonQualificationOutputDtoInterface.class));
		assertThat(dtoRecord.uri() , instanceOf(URI.class));
	}
	
	static Stream<? extends Arguments> excludesQualifications() throws ClassNotFoundException{
		Long naturalPerson_Id = 1L;
		Long legalPerson_Id = 2L;
		
		return Stream.of(
				// combinations of natural persons
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						MANAGER,
						OLD_MANAGER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						FULL_TIME_EMPLOYEE,
						OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						PART_TIME_EMPLOYEE,
						OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						CLIENT,
						OLD_CLIENT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						PROVIDER,
						OLD_PROVIDER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						RESPONSIBLE_FOR_LEGAL_PERSON,
						OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						naturalPerson_Id,
						NATURAL_PERSON, 
						ACCOUNTANT,
						OLD_ACCOUNTANT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				// combinations of legal persons
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						MANAGER,
						OLD_MANAGER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						FULL_TIME_EMPLOYEE,
						OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						PART_TIME_EMPLOYEE,
						OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						CLIENT,
						OLD_CLIENT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						PROVIDER,
						OLD_PROVIDER_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						RESPONSIBLE_FOR_LEGAL_PERSON,
						OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER),
				Arguments.of(
						legalPerson_Id,
						LEGAL_PERSON, 
						ACCOUNTANT,
						OLD_ACCOUNTANT_PERSON_QUALIFICATION, 
						URI_COMPONENTS_BUILDER)	
					);
		}

}
