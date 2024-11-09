package br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.PersonsManagementEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.infrastructure.localDateTime_Setter.LocalDateTime_Setter;

@Service
public class PersonManagement_Service {
	private AuthenticatedUsername authenticatedUsername;
	private LocalDateTime_Setter localDateTime_Setter;

	public PersonManagement_Service(
			AuthenticatedUsername authenticatedUsername,
			LocalDateTime_Setter localDateTime_Setter) {
		this.authenticatedUsername = authenticatedUsername;
		this.localDateTime_Setter = localDateTime_Setter;
	}

	public PersonsManagementEntity create(PersonEntity person, HttpVerbEnum httpVerbEnum) {
		var personManagement = new PersonsManagementEntity();
		personManagement.setPerson(person);
		personManagement.setHttpVerb(httpVerbEnum);
		personManagement.setInitialDate(localDateTime_Setter.nowSetter());
		personManagement.setLoginUser(this.authenticatedUsername.getAuthenticatedUsername());

		return personManagement;
	}	
}
