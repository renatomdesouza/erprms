package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import java.net.URI;

public record DtoRecordToNaturalPerson_With_Uri(
		DtoClassToNaturalPersonOfListing dtoClassToNaturalPersonOfListing,
		URI uri
		){
}
