package br.com.erprms.dtoPort.personDto.personQualificationDto;

import java.net.URI;

import org.springframework.data.domain.Page;

public record DtoRecord_ServicePersonQualification_Page  <T extends PersonQualificationOutputDtoInterface> (
		URI uri, 
		Page<T> page ) {}
