package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PersonService_Uri {
	
	public URI uriBuild(UriComponentsBuilder uriComponentsBuilder, Boolean isNaturalPerson) {
		
		URI uri = null;
		
		if (isNaturalPerson) uri = 
					uriComponentsBuilder.path("/naturalPerson").build().toUri();
		
		if (!isNaturalPerson) uri = 
					uriComponentsBuilder.path("/legalPerson").build().toUri();
		
		return uri;
	}
	
	public URI uriBuild(UriComponentsBuilder uriComponentsBuilder, Long id_Person, Boolean isNaturalPerson) {
		
		URI uri = null;
		
		if (isNaturalPerson) uri = 
					uriComponentsBuilder.path("/naturalPerson/{id}").buildAndExpand(id_Person).toUri();
		
		if (!isNaturalPerson) uri = 
					uriComponentsBuilder.path("/legalPerson/{id}").buildAndExpand(id_Person).toUri();
		
		return uri;
	}
}
