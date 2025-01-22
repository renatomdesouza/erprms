package br.com.erprms.infrastructure.exceptionManager.responseStatusException;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordException {
	
	public static void passwordFailuresException(String password) {
		boolean isPasswordFail = false;
		String passwordFailures = "The password: ";

		if(password.length() < 8) {
			passwordFailures = passwordFailures + "- must be at least eight characters long";
			isPasswordFail = true; }
	
		if(!Pattern.matches(".*[A-Z].*", password)) {
			passwordFailures = passwordFailures + "- must contain at least one capital letter";
			isPasswordFail = true; }
	
		if(!Pattern.matches(".*[a-z].*", password)) {
			passwordFailures = passwordFailures + "- must contain at least one capital letter";
			isPasswordFail = true; }
		
		if(!Pattern.matches(".*[0-9].*", password)) {
			passwordFailures = passwordFailures + "- must contain at least one number";
			isPasswordFail = true; }
	
		if(!Pattern.matches(".*[\\W].*", password)) {
			passwordFailures = passwordFailures + "- must contain at least one special character";
			isPasswordFail = true; }
		
		if(isPasswordFail)
			throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    passwordFailures);
	}
}
