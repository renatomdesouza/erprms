package br.com.erprms.serviceApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

import br.com.erprms.dtoPort.loginDto.LoginDto;
import br.com.erprms.dtoPort.loginDto.LoginDto_Access;
import br.com.erprms.dtoPort.loginDto.LoginDto_CreationAndModification;
import br.com.erprms.infrastructure.springSecurity.TokenManager;

@ExtendWith(MockitoExtension.class)
@WithUserDetails
@ActiveProfiles("test")
class LoginServiceTest {
	@InjectMocks @Spy private LoginService loginService;
	@Mock private CompromisedPasswordChecker compromisedPasswordChecker;
	@Mock private AuthenticationManager authenticationManager;
	@Mock private TokenManager tokenManager;

	@ParameterizedTest
	@MethodSource("loginDto_Provider")
	@DisplayName("Should call the overloaded methods and check if the correct password is compromised")
	void unitTest(LoginDto loginDto) {
		when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.login(),loginDto.password())))
			.thenReturn(autentication_Fake());
		
		switch (loginDto) {
	        case LoginDto_Access loginDto_Access -> {
	        	loginService.authentication((LoginDto_Access)loginDto);
	        	
	        	verify(loginService, times(1)).authentication(loginDto_Access);
	        }

			case LoginDto_CreationAndModification loginDto_CreationAndModification -> {
				when(compromisedPasswordChecker.check(loginDto.password()))
					.thenReturn(new CompromisedPasswordDecision(false))
					.thenReturn(new CompromisedPasswordDecision(true));

				loginService.authentication((LoginDto_CreationAndModification)loginDto);
	        	
	        	CompromisedPasswordException ex = 
        			Assertions.assertThrows(
						CompromisedPasswordException.class,
						() -> loginService.authentication((LoginDto_CreationAndModification)loginDto));
	        	
	        	verify(loginService, times(1)).createOrUpdateLogin_Dammy();
	        	verify(compromisedPasswordChecker, times(2)).check(anyString());
	        	assertThat(ex.getMessage(), is("The provided password is compromised and cannot be used for creation or alteration."));
        	}

			default -> fail("Is not an expected object of the LoginDto interface"); };
	}
	
	static Stream<? extends Arguments> loginDto_Provider() { 
		return Stream.of(
				Arguments.of( new LoginDto_Access("renato@email.com", "rms@342Wrp") ),
				Arguments.of( new LoginDto_CreationAndModification("renato@email.com", "rms@342Wrp")
		));
	}
	
	@SuppressWarnings("serial")
	Authentication autentication_Fake() {
		return new Authentication() {
						@Override public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }
						@Override public Object getCredentials() { return null; }
						@Override public Object getDetails() { return null; }
						@Override public Object getPrincipal() { return null; }
						@Override public boolean isAuthenticated() { return false; }
						@Override public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException { }
						@Override public String getName() { return ""; } }; 
	}
}
