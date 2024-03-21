package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClass_FullTimeEmployeeRegistry {
	private Long person_Id;
	private BigDecimal monthlySalary;
	private SectorEnum sector;
	private String observation;
	
	public DtoClass_FullTimeEmployeeRegistry(DtoRecord_FullTimeEmployeeRegistry manager) {
		this.person_Id = manager.person_Id();
		this.monthlySalary = manager.monthlySalary();
		this.sector = manager.sector();
		this.observation = manager.observation();
	}
}
