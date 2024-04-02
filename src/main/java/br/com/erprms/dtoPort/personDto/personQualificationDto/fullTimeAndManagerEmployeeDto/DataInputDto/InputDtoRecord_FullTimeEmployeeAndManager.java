package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public record InputDtoRecord_FullTimeEmployeeAndManager(
		Long person_Id,
		BigDecimal monthlySalary,
		SectorEnum sector,
		String observation,
		String professionalRegistry
		) {}
