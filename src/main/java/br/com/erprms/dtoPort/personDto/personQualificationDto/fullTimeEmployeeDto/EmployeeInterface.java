package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public interface EmployeeInterface {
	BigDecimal getMonthlySalary();
	SectorEnum getSector();
	String getObservation();
}
