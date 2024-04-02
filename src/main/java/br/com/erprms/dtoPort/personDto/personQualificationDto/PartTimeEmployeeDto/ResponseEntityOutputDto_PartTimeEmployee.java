package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.net.URI;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClass_PartTimeEmployee;

public record ResponseEntityOutputDto_PartTimeEmployee(
		OutputDtoClass_PartTimeEmployee dtoToClass_PartyTimeEmployeeRegistryOutput,
		URI uri ) {

}
