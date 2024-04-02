package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto;

import java.net.URI;

import org.springframework.data.domain.Page;

public record ResponseEntityOutputDtoPage_FullTimeEmployeeAndManager(
		Page<?> pageableDto,
		URI uri) {}
