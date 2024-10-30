package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PersonQualification_CreateUri {

	public URI uriCreator(	UriComponentsBuilder uriComponentsBuilder, 
							String specifiedQualification, 
							Long id) {
		URI uri = null;
		String inicialURI = uriComponentsBuilder.build().toUriString();
		
		uri = URI.create(inicialURI + "/" + specifiedQualification + "/" + String.valueOf(id));
		
		return uri;
		
//		return 	uriComponentsBuilder.path("/" + specifiedQualification + "/{id}")   // return the exception in the tests: “Not enough variables available to expand "id"
//									.buildAndExpand(id)
//									.toUri();
	}
	
	public URI uriCreator(	UriComponentsBuilder uriComponentsBuilder, 
							String specifiedQualification) {
		return  uriComponentsBuilder.path("/" + specifiedQualification)
									.buildAndExpand()
									.toUri();
}
}
