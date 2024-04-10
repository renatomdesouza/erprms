package br.com.erprms.dtoPort.personDto.personQualificationDto;

import java.net.URI;

public record DtoRecord_ServicePersonQualification <U extends PersonQualificationOutputDtoInterface> (		
		URI uri, 
		U dtoOfPerson
		) {}
