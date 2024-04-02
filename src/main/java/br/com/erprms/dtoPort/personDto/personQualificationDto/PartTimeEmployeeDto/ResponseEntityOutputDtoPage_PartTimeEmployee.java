package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.net.URI;

import org.springframework.data.domain.Page;

public record ResponseEntityOutputDtoPage_PartTimeEmployee(
		Page<?> pageableDto,
		URI uri) {
}
