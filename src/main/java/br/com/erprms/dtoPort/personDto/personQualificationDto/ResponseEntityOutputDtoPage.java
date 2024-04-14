package br.com.erprms.dtoPort.personDto.personQualificationDto;

import java.net.URI;

import org.springframework.data.domain.Page;

public record ResponseEntityOutputDtoPage(
		Page<? extends PersonQualificationOutputDtoInterface> pageableDto,
		URI uri) {
}
//