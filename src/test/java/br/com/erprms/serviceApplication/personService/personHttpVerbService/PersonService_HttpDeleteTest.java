package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class PersonService_HttpDeleteTest {
	@InjectMocks
	PersonService_HttpDelete personServiceHttpDelete;

	@Mock
	PersonRepository personRepository;

	@Mock
	PersonsManagementRepository personsManagementRepository;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
