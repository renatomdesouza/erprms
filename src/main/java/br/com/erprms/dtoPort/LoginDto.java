package br.com.erprms.dtoPort;

public record LoginDto(String login, String password) {
	public LoginDto(String login, String password) {
		this.login = login;
		this.password = login+password+login;
	}
}
