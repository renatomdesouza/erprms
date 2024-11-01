package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.testBuilders.Constants_DtosQualifications.FULL_TIME_EMPLOYEE_AND_MANAGER_DTO;
import static br.com.erprms.testBuilders.Constants_Person.URI_COMPONENTS_BUILDER;
import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;
import static br.com.erprms.testBuilders.Constants_PersonQualifications.MANAGER_PERSON_QUALIFICATION;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
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
	@Mock private AuthenticatedUsername authenticatedUsername;

	@Test
	void test() {
		when(personRepository.getReferenceById(anyLong())).thenReturn(NATURAL_PERSON);
		when(managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(any())).thenReturn(MANAGER_PERSON_QUALIFICATION);
		
		var dtoRecord = 
				personQualificationService_HttpPut.update(FULL_TIME_EMPLOYEE_AND_MANAGER_DTO, URI_COMPONENTS_BUILDER, MANAGER);
		
		
	}


	
}
