package br.com.erprms.controllerAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erprms.dtoPort.LoginDto;
import br.com.erprms.dtoPort.TokenDto;
import br.com.erprms.serviceApplication.LoginService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
	private final LoginService loginService;
	
	private LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping
	public ResponseEntity<TokenDto> efetuarLogin(@RequestBody LoginDto loginDto) {   
	    	return ResponseEntity.ok(
	        		loginService.autentication(loginDto));
	}
}
