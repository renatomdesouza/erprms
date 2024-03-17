package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.net.URI;

import br.com.erprms.dtoPort.personDto.PersonListingDto;

public record DtoRecord_ServicePerson <T extends PersonListingDto> (
		URI uri, 
		T dtoOfPerson) {}
