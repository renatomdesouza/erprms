package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constants_PersonQualification.FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPerson;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PersonQualificationService_HttpPostTest {
	@InjectMocks @Spy private PersonQualificationService_HttpPost personQualificationService_HttpPost;
	@Spy private ModelMapper mapper;
	@Mock private PersonQualificationExceptions exceptionService;
	@Mock private PersonRepository personRepository;
	@Mock private PersonQualificationRepository personQualificationRepository;
	@Mock private StatusPerson statusPerson;
	@Mock private AuthenticatedUsername authenticatedUsername;
	
	@Captor ArgumentCaptor<PersonQualificationSuperclassEntity> personQualificationCaptor;
	
	
	@Test
	void Test01() {
		when(personRepository.getReferenceById(anyLong())).thenReturn(NATURAL_PERSON);
		when(authenticatedUsername.getAuthenticatedUsername()).thenReturn("any user");
		
		var dtoRecord = personQualificationService_HttpPost
							.registerService(FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS, URI_COMPONENTS_BUILDER, MANAGER);

		verify(mapper, times(1)).map(FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS, ManagerPersonQualification.class);
		verify(personQualificationRepository, times(1)).save(any());
		verify(statusPerson, times(1)).setStatusOfUse(any());
		
		assertThat(dtoRecord, instanceOf(DtoRecord_ServicePersonQualification.class));
		assertThat(dtoRecord.dtoOfPerson(), instanceOf(OutputDtoClass_FullTimeEmployeeAndManager.class));
		assertThat(dtoRecord.uri() , instanceOf(URI.class)); 
	}
	

}
