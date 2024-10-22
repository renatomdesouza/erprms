package br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.PersonsManagementEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;

@Service
public class CreatePersonManagement {
	private AuthenticatedUsername authenticatedUsername;

	public CreatePersonManagement(AuthenticatedUsername authenticatedUsername) {
		this.authenticatedUsername = authenticatedUsername;
	}

	public PersonsManagementEntity create(PersonEntity person, HttpVerbEnum httpVerbEnum) {
		var personManagement = new PersonsManagementEntity();
		personManagement.setPerson(person);
		personManagement.setHttpVerb(httpVerbEnum);
		personManagement.setInitialDate(clockForNow());
		personManagement.setLoginUser(this.authenticatedUsername.getAuthenticatedUsername());

		return personManagement;
	}

	protected LocalDateTime clockForNow() {
		return LocalDateTime.now();
	}
	
}
