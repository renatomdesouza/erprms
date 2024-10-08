package br.com.erprms.serviceApplication.personService.personHttpVerbService;
////
import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticationFacade;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonService_HttpPostTest {
	@InjectMocks PersonService_HttpPost<PersonListingDto> personServiceHttpPost;
	@Spy ModelMapper mapper;

	@Spy PersonRepository personRepository;
	@Mock PersonEntity personEntity;
	@Spy UriComponentsBuilder uriComponentsBuilder;
	@Spy PersonExceptions personException;
	@Spy AuthenticationFacade authenticationFacade;

	@ParameterizedTest
	@MethodSource("dtoRecord_NaturalPersonOfRegistry")
	<T> void test01(T personDto) {
		var person = personServiceHttpPost.createPerson(personDto);

		assertThat(personDto, instanceOf(DtoRecord_NaturalPersonOfRegistry.class));
		assertThat(person, instanceOf(PersonEntity.class));
		assertNotNull(person);
		
		
//		System.out.println( " ===========> " + person.getCpfCnpj());
//
//		assertEquals(person.getFullNameOrEntityName(), "José da Silva");
//		assertEquals(person.getNickname(), "Silva");
//		assertEquals(person.getCpfCnpj(), "12345678912");
//		assertEquals(person.getEmail(), "josesilva@email.com.br");
//		assertEquals(person.getSite(), "www.silva.com.br");
//		assertEquals(person.getDateBorn(),"20/01/1980");
//		assertEquals(person.getMaritalStatus(),"casado");
//		assertEquals(person.getCityBorn(), "São Paulo");
//		assertEquals(person.getCountryBorn(),"Brasil");
//		assertEquals(person.getSex(), SexEnum.MASCULINE);
//		assertEquals(person.getStreet(),"Rua Das Alamedas");
//		assertEquals(person.getNumber(),"0001");
//		assertEquals(person.getNeighborhood(),"Bairro das Alamedas");
//		assertEquals(person.getComplement(),"não há");
//		assertEquals(person.getPostalCode(),"01153-000");
//		assertEquals(person.getCityAndStateOrProvince(),"São Paulo/SP");

	}


	static Stream<? extends Arguments> dtoRecord_NaturalPersonOfRegistry(){
		return Stream.of( Arguments.of(
				new DtoRecord_NaturalPersonOfRegistry(
						"José da Silva",
						"Silva",
						"12345678912",
						"josesilva@email.com.br",
						"www.silva.com.br",
						"20/01/1980",
						"casado",
						"São Paulo",
						"Brasil",
						"MASCULINE",
						"Rua Das Alamedas",
						"0001",
						"Bairro das Alamedas",
						"não há",
						"01153-000",
						"São Paulo/SP")));
	}


	@ParameterizedTest
	@MethodSource("dtoRecord_LegalPersonOfRegistry")
	<T> void test02(T personDto) {
		var person = personServiceHttpPost.createPerson(personDto);

		assertThat(personDto, instanceOf(DtoRecord_LegalPersonOfRegistry.class));
		assertThat(person, instanceOf(PersonEntity.class));
		assertNotNull(person);

//		assertEquals(person.getCpfCnpj(), 12345678912345L);
	}

	static Stream<? extends Arguments> dtoRecord_LegalPersonOfRegistry(){
		return Stream.of( Arguments.of(
				new DtoRecord_LegalPersonOfRegistry(
						"Empresa Jurídica SA",
						"Empresa",
						"12345678912345",
						"empresasa@email.com.br",
						"www.empresasa.com.br",
						"123456789",
						"12345678912345",
						"Rua da Empresa",
						"0001",
						"Bairro Empresarial",
						"Não Possui",
						"01153-000",
						"São Paulo/SP")));
	}



/*
	Try something like

	@Mock
	UriComponentsBuilder uriComponentsBuilder;
	@Mock
	UriComponents uriComponents;

    Mockito.when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
    Mockito.when(uriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(uriCompo
*/
}
