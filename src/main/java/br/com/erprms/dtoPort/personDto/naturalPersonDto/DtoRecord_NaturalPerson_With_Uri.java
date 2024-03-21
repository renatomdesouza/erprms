package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import java.net.URI;

public record DtoRecord_NaturalPerson_With_Uri(
		DtoClass_NaturalPersonOfListing dtoClassToNaturalPersonOfListing,
		URI uri
		){
}
