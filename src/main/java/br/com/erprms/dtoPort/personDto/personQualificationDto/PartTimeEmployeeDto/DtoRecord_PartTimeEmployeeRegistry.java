package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public record DtoRecord_PartTimeEmployeeRegistry(
		Long person_Id,
		BigDecimal hourlyRate,
		SectorEnum sector,
		String observation,
		String professionalRegistry) {

}
