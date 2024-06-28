package br.com.erprms.dtoPort;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;

public record LoginDto(String login, String password) {
	public LoginDto(
			@NonNull @Email
			String login,

			@NonNull @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
			String password
		) {
		this.login = login;
		this.password = login+password+login;
	}
}
