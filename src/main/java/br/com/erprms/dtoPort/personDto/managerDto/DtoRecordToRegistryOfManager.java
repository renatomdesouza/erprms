package br.com.erprms.dtoPort.personDto.managerDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public record DtoRecordToRegistryOfManager(
		Long person_Id,
		BigDecimal salary,
		SectorEnum sector,
		String observation
		) {}
