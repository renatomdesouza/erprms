package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto;

import java.net.URI;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutPutExcludeDto_FullTimeEmployeeAndManager;

public record ResponseEntityOutputDtoExclude_FullTimeEmployeeAndManager(
		OutPutExcludeDto_FullTimeEmployeeAndManager outPutExcludeDto_FullTimeEmployeeAndManager,
		URI uri ) {}
