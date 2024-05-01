package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
//import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.EmployeeInterface;
import lombok.Getter;

@Getter
public class InputDtoClass_FullTimeEmployeeAndManager implements PersonQualificationInputDtoInterface{
	private final Long person_Id;
	private final BigDecimal monthlySalary;
	private final SectorEnum sector;
	private final String observation;
	private final String professionalRegistry;
	
	public InputDtoClass_FullTimeEmployeeAndManager(InputDtoRecord_FullTimeEmployeeAndManager manager) {
		this.person_Id = manager.person_Id();
		this.monthlySalary = manager.monthlySalary();
		this.sector = manager.sector();
		this.observation = manager.observation();
		this.professionalRegistry = manager.professionalRegistry();
	}
}
