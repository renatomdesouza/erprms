package br.com.erprms.controllerAdapter.personController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPost;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPut;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

@SpringBootTest
@ActiveProfiles("test")
@WithMockUser
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NaturalPersonControllerTest {
	@InjectMocks
	private PersonService_HttpPost<? extends PersonListingDto> personPost;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private PersonEntity personEntity;

	@Autowired
	private JacksonTester<DtoRecord_NaturalPersonOfRegistry> jacksonTester_As_DtoRecord_NaturalPersonOfRegistry;

	@Test
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@DisplayName("Should return 201 for save the user")
	void integrityTest_CorrectAccessToPost()  throws Exception {
		MockHttpServletResponse response =
				mockMvc.perform(
						post("/naturalPerson")
										.content(jacksonTester_As_DtoRecord_NaturalPersonOfRegistry
												.write(dataFromNaturalPersonRegistry_Of_SaveOk).getJson())
										.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(201, response.getStatus());
	}

	@Test
	@Order(Ordered.LOWEST_PRECEDENCE)
	@DisplayName("Should return 500 for the user's email is already registered in the system")
	void integrityTest_IncorrectAccessToPost_WithEmail()  throws Exception {
		MockHttpServletResponse response =
				mockMvc.perform(
								post("/naturalPerson")
										.content(jacksonTester_As_DtoRecord_NaturalPersonOfRegistry
												.write(dataFromNaturalPersonRegistry_Of_FailureForEmail).getJson())
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
										.content(jacksonTester_As_DtoRecord_NaturalPersonOfRegistry
												.write(dataFromNaturalPersonRegistry_Of_FailureForCpf).getJson())
										.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(500, response.getStatus());
	}

	@Test
	@DisplayName("Should return 201 when accessing the url correctly")
	void unitTest_CorrectAccessToListing_WithUrl() throws Exception {
		MockHttpServletResponse response =
				mockMvc	.perform(get("/naturalPerson"))
						.andReturn()
						.getResponse();
		assertEquals(201, response.getStatus());
	}

	@Test
	@DisplayName("Should return 404 when accessing URL incorrectly")
	void unitTest_IncorrectAccessToListing_WithUrl() throws Exception {
		MockHttpServletResponse response =
				mockMvc	.perform(get("/xxxxx"))
						.andReturn()
						.getResponse();
		assertEquals(404, response.getStatus());
	}

	DtoRecord_NaturalPersonOfRegistry dataFromNaturalPersonRegistry_Of_SaveOk = new DtoRecord_NaturalPersonOfRegistry(
			"Martim Afonso",
			"Tibiriçá",
			72145656812L,
			"caciquetibirica@email.com",
			"museudoipiranga.org.br",
			"29/12/1520",
			"Solteiro",
			"São Paulo-SP",
			"brasileira",
			SexEnum.MASCULINE,
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
			82145656812L,
			"caciquetibirica@email.com",
			"museudoipiranga.org.br",
			"29/12/1520",
			"Solteiro",
			"São Paulo-SP",
			"brasileira",
			SexEnum.MASCULINE,
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
			72145656812L,
			"ccaciquetibirica@email.com",
			"museudoipiranga.org.br",
			"29/12/1520",
			"Solteiro",
			"São Paulo-SP",
			"brasileira",
			SexEnum.MASCULINE,
			"Praça da Sé",
			"sem numero",
			"Catedral da Sé",
			"não há",
			"01001-000",
			"São Paulo-SP"
	);
}
