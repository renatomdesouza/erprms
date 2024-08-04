package br.com.erprms.controllerAdapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import br.com.erprms.domainModel.LoginUser;
import br.com.erprms.repositoryAdapter.LoginRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.serviceApplication.personService.personHttpVerbService.PersonService_HttpPost;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class LoginControllerTest {
	@InjectMocks
	private PersonService_HttpPost<? extends PersonListingDto> personPost;

	@Autowired
	private MockMvc mockMvc;

	private record LoginUserTestDto (String login, String password){};

	@Autowired
	private JacksonTester<LoginUserTestDto> json_LoginDto;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginRepository loginRepository;

	private LoginUser testLoginUser = new LoginUser();

	@BeforeEach
	public void setUp() {
		String login = "testLoginUser@email.com";
		String rawPassword = "123456";
		testLoginUser.setLogin(login);
		testLoginUser.setPassword(passwordEncoder.encode(login+rawPassword+login));
		loginRepository.save(testLoginUser);
	}

	@AfterEach
	public void tearDown() {
		loginRepository.delete(testLoginUser);
	}

	@Test
	@DisplayName("Should return 200 for connecting with username and password")
	void IntegrityTest_ConnectionOk() throws Exception {
		var loginUserTestDto =
				new LoginUserTestDto("testLoginUser@email.com", "123456");

		MockHttpServletResponse response =
				mockMvc	.perform(
							post("/login")
									.content(json_LoginDto.write(loginUserTestDto).getJson())
									.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	@DisplayName("Should return 401 for incorrect username and password")
	void IntegrityTest_ConnectionFail() throws Exception {
		var loginUserTestDto =
				new LoginUserTestDto("xxxxx", "xxxxx");

		MockHttpServletResponse response =
				mockMvc	.perform(
								post("/login")
										.content(json_LoginDto.write(loginUserTestDto).getJson())
										.contentType(MediaType.APPLICATION_JSON) )
						.andReturn()
						.getResponse();

		assertEquals(401, response.getStatus());
	}
}
