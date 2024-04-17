package br.com.erprms.serviceApplication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.LoginUser;
import br.com.erprms.dtoPort.LoginDto;
import br.com.erprms.dtoPort.TokenDto;
import br.com.erprms.infrastructure.springSecurity.TokenManager;

@Service
public class LoginService {
	private final AuthenticationManager manager;
	private final TokenManager tokenService;
	
	private LoginService(AuthenticationManager manager, TokenManager tokenService) {
		this.manager = manager;
		this.tokenService = tokenService;
	}
	
	public TokenDto autentication(LoginDto userDto) {
		var authentication = 
				manager.authenticate(
					new UsernamePasswordAuthenticationToken(
							userDto.login(), 
							userDto.password()));
		
		return new TokenDto(
						tokenService.generateToken(
								(LoginUser) authentication.getPrincipal()));
	}
}


