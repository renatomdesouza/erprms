package br.com.erprms.integrationTests_ControllerAdapter.personController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

@SpringBootTest
@ActiveProfiles("test")
@WithMockUser
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LegalPersonControllerTest {
	@Autowired private MockMvc mockMvc;
	
	@Autowired private PersonRepository personRepository;

	@Autowired private JacksonTester<DtoRecord_LegalPersonOfRegistry> jacksonTester_As_DtoRecord_LegalPersonOfRegistry;
	@Autowired private ObjectMapper objectMapper;	// alternative to JacksonTester
	
	@Test
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@DisplayName("Should return 201 for save the user")
	void integrityTest_CorrectAccessToPost_LegalPerson()  throws Exception {
		mockMvc.perform(
					post("/legalPerson")
						.content(
								objectMapper.writeValueAsString(dataFromLegalPersonRegistry_Of_FailureForEmail))
						.contentType(MediaType.APPLICATION_JSON) )
				.andExpect(status().is(201));
		
		PersonEntity recordedPerson = personRepository.getReferenceById(1L);
		
		assertEquals(1L, recordedPerson.getId());
		assertEquals("Empresa Fulana SA", recordedPerson.getFullNameOrEntityName());
		assertEquals("EmpFulana", recordedPerson.getNickname());
		assertEquals(85345678901237L, recordedPerson.getCpfOrCnpj());
		assertEquals("fulana5@mail.com", recordedPerson.getEmail());
		assertEquals("www.fulana10.com", recordedPerson.getSite());
		assertEquals("232323232", recordedPerson.getInscricEstad());
		assertEquals("45454545454545", recordedPerson.getInscricMunicip());
		assertEquals("rua Sem Nome", recordedPerson.getStreet());
		assertEquals("010101", recordedPerson.getNumber());
		assertEquals("Centro", recordedPerson.getNeighborhood());
		assertEquals("Praça Central", recordedPerson.getComplement());
		assertEquals("01010-101", recordedPerson.getPostalCode());
		assertEquals("São Paulo/SP", recordedPerson.getCityAndStateOrProvince());
		
		assertEquals(false, recordedPerson.getIsNaturalPerson());
		assertEquals(StatusPersonalUsedEnum.NOT_USED, recordedPerson.getStatusPersonEnum());

		assertNull(recordedPerson.getDateBorn());
        assertNull(recordedPerson.getMaritalStatus());
        assertNull(recordedPerson.getCityBorn());
        assertNull(recordedPerson.getSex());
		assertNull(recordedPerson.getAdditionalAddressEntity());
	}

	@Test
	@Order(Ordered.LOWEST_PRECEDENCE)
	@DisplayName("Should return 500 for the user's email is already registered in the system")
	void integrityTest_IncorrectAccessToPost_WithEmail()  throws Exception {
		MockHttpServletResponse response =
				mockMvc.perform(
								post("/legalPerson")
										.content(jacksonTester_As_DtoRecord_LegalPersonOfRegistry
												.write(dataFromLegalPersonRegistry_Of_FailureForEmail).getJson())
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
								post("/legalPerson")
										.content(jacksonTester_As_DtoRecord_LegalPersonOfRegistry
												.write(getDataFromLegalPersonRegistry_Of_FailureForCpf).getJson())
										.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(500, response.getStatus());
	}

	@Test
	@DisplayName("Should return 201 when accessing the url correctly")
	void integrityTest_CorrectAccessToListing_WithUrl() throws Exception {
		MockHttpServletResponse response =
				mockMvc	.perform(get("/legalPerson"))
						.andReturn()
						.getResponse();
		assertEquals(201, response.getStatus());
	}

	@Test
	@DisplayName("Should return 404 when accessing URL incorrectly")
	void integrityTest_IncorrectAccessToListing_WithUrl() throws Exception {
		MockHttpServletResponse response =
				mockMvc	.perform(get("/legalPer_xxxxx"))
						.andReturn()
						.getResponse();
		assertEquals(500, response.getStatus());
	}

	DtoRecord_LegalPersonOfRegistry dataFromLegalPersonRegistry_Of_SaveOk = new DtoRecord_LegalPersonOfRegistry(
			"Empresa Fulana SA",
			"EmpFulana",
			"95345678901234",
			"fulana5@mail.com",
			"www.fulana10.com",
			"232323232",
			"45454545454545",
			"rua Sem Nome",
			"010101",
			"Centro",
			"Praça Central",
			"01010-101",
			"São Paulo/SP"
	);

	DtoRecord_LegalPersonOfRegistry dataFromLegalPersonRegistry_Of_FailureForEmail = new DtoRecord_LegalPersonOfRegistry(
			"Empresa Fulana SA",
			"EmpFulana",
			"85345678901237",
			"fulana5@mail.com",
			"www.fulana10.com",
			"232323232",
			"45454545454545",
			"rua Sem Nome",
			"010101",
			"Centro",
			"Praça Central",
			"01010-101",
			"São Paulo/SP"
	);

	DtoRecord_LegalPersonOfRegistry getDataFromLegalPersonRegistry_Of_FailureForCpf = new DtoRecord_LegalPersonOfRegistry(
			"Empresa Fulana SA",
			"EmpFulana",
			"95345678901237",
			"fulana5@mail.com",
			"www.fulana11.com",
			"232323232",
			"45454545454545",
			"rua Sem Nome",
			"010101",
			"Centro",
			"Praça Central",
			"01010-101",
			"São Paulo/SP"
	);
}
