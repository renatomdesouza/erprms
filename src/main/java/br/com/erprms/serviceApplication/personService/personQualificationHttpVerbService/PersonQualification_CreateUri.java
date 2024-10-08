package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PersonQualification_CreateUri {

	public URI uriCreator(	UriComponentsBuilder uriComponentsBuilder, 
							String specifiedQualification, 
							Long id) {
		return  uriComponentsBuilder.path("/" + specifiedQualification + "/{id}")
									.buildAndExpand(id)
									.toUri();
	}
	
	public URI uriCreator(	UriComponentsBuilder uriComponentsBuilder, 
							String specifiedQualification) {
		return  uriComponentsBuilder.path("/" + specifiedQualification)
									.buildAndExpand()
									.toUri();
	}
}
