package br.com.erprms.integrationTests_ControllerAdapter.personController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
 import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

@SpringBootTest
@ActiveProfiles("test")
@WithMockUser
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NaturalPersonControllerTest {
	@Autowired private MockMvc mockMvc;
	
	@Autowired private PersonRepository personRepository;

	@Autowired private JacksonTester<DtoRecord_NaturalPersonOfRegistry> jacksonTester;
	@Autowired private ObjectMapper objectMapper;	// alternative to JacksonTester

	
	@Test
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@DisplayName("Should return 201 for save the user")
	void integrityTest_CorrectAccessToPost_NaturalPerson()  throws Exception {
		mockMvc.perform(
					post("/naturalPerson")
						.content(
								objectMapper.writeValueAsString(dataFromNaturalPersonRegistry_Of_SaveOk))
						.contentType(MediaType.APPLICATION_JSON) )
				.andExpect(status().is(201));
		
		PersonEntity recordedPerson = personRepository.getReferenceById(2L);
		
		assertEquals(2L, recordedPerson.getId());
		assertEquals("Martim Afonso", recordedPerson.getFullNameOrEntityName());
		assertEquals("Tibiriçá", recordedPerson.getNickname());
		assertEquals(72145656812L, recordedPerson.getCpfOrCnpj());
		assertEquals("caciquetibirica@email.com", recordedPerson.getEmail());
		assertEquals("museudoipiranga.org.br", recordedPerson.getSite());
		assertEquals("29/12/1520", recordedPerson.getDateBorn());
		assertEquals("Solteiro", recordedPerson.getMaritalStatus());
		assertEquals("São Paulo SP", recordedPerson.getCityBorn());
		assertEquals("brasil eira", recordedPerson.getCountryBorn());
		assertEquals(SexEnum.MASCULINE, recordedPerson.getSex());
		assertEquals("Praça da Sé", recordedPerson.getStreet());
		assertEquals("sem numero", recordedPerson.getNumber());
		assertEquals("Catedral da Sé", recordedPerson.getNeighborhood());
		assertEquals("não há", recordedPerson.getComplement());
		assertEquals("01001-000", recordedPerson.getPostalCode());
		assertEquals("São Paulo-SP", recordedPerson.getCityAndStateOrProvince());
		
		assertEquals(true, recordedPerson.getIsNaturalPerson());
		assertEquals(StatusPersonalUsedEnum.NOT_USED, recordedPerson.getStatusPersonEnum());
        assertNull(recordedPerson.getInscricEstad());
        assertNull(recordedPerson.getInscricMunicip());
		assertNull(recordedPerson.getAdditionalAddressEntity());
	}

	@Test
	@Order(Ordered.LOWEST_PRECEDENCE)
	@DisplayName("Should return 500 for the user's email is already registered in the system")
	void integrityTest_IncorrectAccessToPost_WithEmail()  throws Exception {
		MockHttpServletResponse response =
				mockMvc.perform(
							post("/naturalPerson")
									.content(
											jacksonTester.write(dataFromNaturalPersonRegistry_Of_FailureForEmail).getJson())
									.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(500, response.getStatus());
	}

	@Test
	@Order(Ordered.LOWEST_PRECEDENCE)
	@DisplayName("Should return 500 for the user's CPF is already registered in the system")
	void integrityTest_IncorrectAccessToPost_WithCpf()  throws Exception {
		MockHttpServletResponse response =
				mockMvc.perform(
							post("/naturalPerson")
									.content(
											jacksonTester.write(dataFromNaturalPersonRegistry_Of_FailureForCpf).getJson())
									.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(500, response.getStatus());
	}

	@Test
	@DisplayName("Should return 201 when accessing the url correctly")
	void integrityTest_CorrectAccessToListing_WithUrl() throws Exception {
		MockHttpServletResponse response =
				mockMvc	.perform(get("/naturalPerson"))
						.andReturn()
						.getResponse();
		assertEquals(201, response.getStatus());
	}

	@Test
	@DisplayName("Should return 404 when accessing URL incorrectly")
	void integrityTest_IncorrectAccessToListing_WithUrl() throws Exception {
		MockHttpServletResponse response =
				mockMvc	.perform(get("/xxxxx"))
						.andReturn()
						.getResponse();
		
		assertEquals(500, response.getStatus());
	}

	DtoRecord_NaturalPersonOfRegistry dataFromNaturalPersonRegistry_Of_SaveOk = new DtoRecord_NaturalPersonOfRegistry(
			"Martim Afonso",
			"Tibiriçá",
			"72145656812",
			"caciquetibirica@email.com",
			"museudoipiranga.org.br",
			"29/12/1520",
			"Solteiro",
			"São Paulo SP",
			"brasil eira",
			"MASCULINE",
			"Praça da Sé",
			"sem numero",
			"Catedral da Sé",
			"não há",
			"01001-000",
			"São Paulo-SP"
	);

	DtoRecord_NaturalPersonOfRegistry dataFromNaturalPersonRegistry_Of_FailureForEmail = new DtoRecord_NaturalPersonOfRegistry(
			"Martim Afonso",
			"Tibiriçá",
			"82145656812",
			"caciquetibirica@email.com",
			"museudoipiranga.org.br",
			"29/12/1520",
			"Solteiro",
			"São Paulo-SP",
			"brasileira",
			"MASCULINE",
			"Praça da Sé",
			"sem numero",
			"Catedral da Sé",
			"não há",
			"01001-000",
			"São Paulo-SP"
	);

	DtoRecord_NaturalPersonOfRegistry dataFromNaturalPersonRegistry_Of_FailureForCpf = new DtoRecord_NaturalPersonOfRegistry(
			"Martim Afonso",
			"Tibiriçá",
			"72145656812",
			"ccaciquetibirica@email.com",
			"museudoipiranga.org.br",
			"29/12/1520",
			"Solteiro",
			"São Paulo-SP",
			"brasileira",
			"MASCULINE",
			"Praça da Sé",
			"sem numero",
			"Catedral da Sé",
			"não há",
			"01001-000",
			"São Paulo-SP"
	);
}
