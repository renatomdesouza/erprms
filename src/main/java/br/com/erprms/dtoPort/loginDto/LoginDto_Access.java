package br.com.erprms.dtoPort.loginDto;

import static br.com.erprms.infrastructure.exceptionManager.responseStatusException.PasswordException.passwordFailuresException;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto_Access (
		@NotBlank @Email
		String login, 
		
		@NotBlank
		String password) implements LoginDto {
	
	public LoginDto_Access(String login, String password) {
		this.login = login;
		this.password = password;
		passwordFailuresException(this.password);
	}
}
