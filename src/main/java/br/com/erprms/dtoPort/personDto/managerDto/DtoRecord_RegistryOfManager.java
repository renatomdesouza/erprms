package br.com.erprms.dtoPort.personDto.managerDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public record DtoRecord_RegistryOfManager(
		Long person_Id,
		BigDecimal monthlySalary,
		SectorEnum sector,
		String observation
		) {}