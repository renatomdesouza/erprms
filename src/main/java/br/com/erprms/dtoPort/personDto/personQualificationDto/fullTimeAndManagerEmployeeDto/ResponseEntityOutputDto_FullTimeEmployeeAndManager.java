package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto;

import java.net.URI;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputDtoClass_FullTimeEmployeeAndManager;

public record ResponseEntityOutputDto_FullTimeEmployeeAndManager (
		OutputDtoClass_FullTimeEmployeeAndManager dtoClassToOutputFullTimeEmployeeOfRegistry,
		URI uri ) {}
