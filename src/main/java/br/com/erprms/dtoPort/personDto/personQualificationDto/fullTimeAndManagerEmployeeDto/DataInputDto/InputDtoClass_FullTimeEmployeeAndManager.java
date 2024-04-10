package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.EmployeeInterface;
import lombok.Getter;

@Getter
public class InputDtoClass_FullTimeEmployeeAndManager implements EmployeeInterface, PersonQualificationInputDtoInterface{
	private Long person_Id;
	private BigDecimal monthlySalary;
	private SectorEnum sector;
	private String observation;
	private String professionalRegistry;
	
	public InputDtoClass_FullTimeEmployeeAndManager(InputDtoRecord_FullTimeEmployeeAndManager manager) {
		this.person_Id = manager.person_Id();
		this.monthlySalary = manager.monthlySalary();
		this.sector = manager.sector();
		this.observation = manager.observation();
		this.professionalRegistry = manager.professionalRegistry();
	}
}
