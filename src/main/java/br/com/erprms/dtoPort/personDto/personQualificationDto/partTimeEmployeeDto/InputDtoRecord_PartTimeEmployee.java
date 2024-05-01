package br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public record InputDtoRecord_PartTimeEmployee (
		Long person_Id,
		BigDecimal hourlyRate,
		SectorEnum sector,
		String observation,
		String professionalRegistry) {}
