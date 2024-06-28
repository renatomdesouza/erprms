package br.com.erprms.serviceApplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
class LoginServiceTest {

    @MockBean
    private LoginService loginService;

    @Test
    @DisplayName("should return 200 for logged in user")
    void userConnectionSuccess(){
        
    }

}