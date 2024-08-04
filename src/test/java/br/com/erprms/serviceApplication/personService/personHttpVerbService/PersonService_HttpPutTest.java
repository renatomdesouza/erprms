package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPut;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.util.UriComponentsBuilder;

//@DataJpaTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonService_HttpPutTest {
// .PersonRepository,org.modelmapper.ModelMapper,br.com.erprms.repositoryAdapter.personRepository.PersonsManagementRepository,br.com.erprms.infrastructure.getAuthentication.AuthenticationFacade,br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions
	@InjectMocks
	PersonService_HttpPut service;

	@Mock
	PersonRepository personRepository;

	@Mock
	PersonsManagementRepository personsManagementRepository;

	@Mock
	PersonEntity personEntity;

	@Mock
	PersonListingDto personListingDto;

	@Mock
	DtoRecord_ServicePerson dtoRecordServicePerson;

	@Mock
	DtoRecord_NaturalPersonOfUpdate dtoRecordNaturalPersonOfUpdate;

	@Mock
	UriComponentsBuilder uriComponentsBuilder;

	@Captor
	ArgumentCaptor<PersonService_HttpPut<PersonListingDto>> captor;

	@Test
	void test() {
		var person = new PersonEntity();
		when(personRepository.getReferenceById(1L)).thenReturn(personEntity);
//		Assertions.assertThat(personEntity, isA(PersonEntity.class));
//		Assertions.assertThat(personEntity, is(personEntity));
		assertSame(personEntity, personEntity);

//		assertSame();

		///////////////////////
//
//		when(service.updateService(dtoRecordNaturalPersonOfUpdate, 1L, uriComponentsBuilder));
////				.thenReturn(dtoRecordServicePerson);
//		verify(service).updateService(captor.capture());

		//		when(service.updateService()).thenReturn();


		// fail("Not yet implemented");
	}

}
