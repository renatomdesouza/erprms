package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.net.URI;

import org.springframework.data.domain.Page;

public record DtoRecord_PartTimeEmployeeOutputPage_With_Uri(
		Page<DtoClass_PartTimeEmployeeToListing> pageableDto,
		URI uri) {

}
