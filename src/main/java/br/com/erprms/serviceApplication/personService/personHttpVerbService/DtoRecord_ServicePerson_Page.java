package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import java.net.URI;

import org.springframework.data.domain.Page;

import br.com.erprms.dtoPort.personDto.PersonListingDto;

public record DtoRecord_ServicePerson_Page <T extends PersonListingDto> (
		URI uri, 
		Page<T> page ) {}