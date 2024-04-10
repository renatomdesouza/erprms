package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.net.URI;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClassExclude_PartTimeEmployee;

public record ResponseEntityOutputDtoExclude_PartTimeEmployee(
		OutputDtoClassExclude_PartTimeEmployee outputDtoClassExclude_PartTimeEmployee,
		URI uri ) {}
