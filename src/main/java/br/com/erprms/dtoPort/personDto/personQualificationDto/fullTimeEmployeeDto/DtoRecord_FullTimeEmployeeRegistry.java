package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public record DtoRecord_FullTimeEmployeeRegistry(
		Long person_Id,
		BigDecimal monthlySalary,
		SectorEnum sector,
		String observation
		) {}
