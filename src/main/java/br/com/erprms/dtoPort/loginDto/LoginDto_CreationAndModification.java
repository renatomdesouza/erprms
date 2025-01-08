package br.com.erprms.dtoPort.loginDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto_CreationAndModification(
		@NotBlank @Email
		String login, 
		
		@NotBlank
		String password) implements LoginDto{
}
