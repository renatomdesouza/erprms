package br.com.erprms.controllerAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erprms.dtoPort.TokenDto;
import br.com.erprms.dtoPort.loginDto.LoginDto_Access;
import br.com.erprms.serviceApplication.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	private final LoginService loginService;
	
	private LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping
	public ResponseEntity<TokenDto> efetuarLogin(@RequestBody LoginDto_Access loginDto) {   
	    	return ResponseEntity.ok(
	        		loginService.authentication(loginDto));
	}
}
