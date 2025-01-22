package br.com.erprms.serviceApplication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.LoginUser;
import br.com.erprms.dtoPort.TokenDto;
import br.com.erprms.dtoPort.loginDto.LoginDto;
import br.com.erprms.dtoPort.loginDto.LoginDto_CreationAndModification;
import br.com.erprms.infrastructure.springSecurity.TokenManager;

@Service
public class LoginService {
	private final CompromisedPasswordChecker compromisedPasswordChecker;
	private final AuthenticationManager authenticationManager;
	private final TokenManager tokenManager;
	
	private LoginService(
			CompromisedPasswordChecker compromisedPasswordChecker,
			AuthenticationManager authenticationManager, 
			TokenManager tokenManager) {
		this.compromisedPasswordChecker = compromisedPasswordChecker;
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
	}

	public TokenDto authentication(LoginDto loginDto) {
		if(loginDto instanceof LoginDto_CreationAndModification) {
			CompromisedPasswordDecision compromisedPasswordDecision = 
					compromisedPasswordChecker.check(loginDto.password());

			if (compromisedPasswordDecision.isCompromised()) {
			    throw new CompromisedPasswordException(
			    		"The provided password is compromised and cannot be used for creation or alteration.");}
			
			createOrUpdateLogin_Dammy();
		}
		
		var authentication = 
				authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginDto.login(), 
							loginDto.password()));
		
		return new TokenDto(
						tokenManager.generateToken(
								(LoginUser) authentication.getPrincipal()));
	}
	
	protected void createOrUpdateLogin_Dammy() {
		// The update or creation logic will still be developed.
	}
}
