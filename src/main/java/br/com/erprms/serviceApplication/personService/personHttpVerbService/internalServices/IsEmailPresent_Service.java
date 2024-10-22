package br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices;

import org.springframework.stereotype.Service;

import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

@Service
public class IsEmailPresent_Service {
	private PersonRepository personRepository;
	
	public IsEmailPresent_Service(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public boolean isEmailPresent(String oldEmail) {
		boolean emailAlreadyRegistered = false;
		
		var existingEmail = personRepository.findByEmail(oldEmail);
		
		if(existingEmail != null)
			emailAlreadyRegistered = true;
		
		return emailAlreadyRegistered;
	}
	
	public boolean isEmailPresent(String oldEmail, String actualEmail) {
		if( oldEmail.equals(actualEmail) || (actualEmail == null) ) 
			return false;
		
		return isEmailPresent(oldEmail);
	}
}
