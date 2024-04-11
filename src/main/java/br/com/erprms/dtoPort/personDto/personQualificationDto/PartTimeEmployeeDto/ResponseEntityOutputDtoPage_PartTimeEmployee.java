package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.net.URI;

import org.springframework.data.domain.Page;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;

public record ResponseEntityOutputDtoPage_PartTimeEmployee(
		Page<? extends PersonQualificationOutputDtoInterface> pageableDto,
		URI uri) {
}
