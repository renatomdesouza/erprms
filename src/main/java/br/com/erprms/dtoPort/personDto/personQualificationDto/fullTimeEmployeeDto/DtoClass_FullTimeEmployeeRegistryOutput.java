package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClass_FullTimeEmployeeRegistryOutput {
	private String specified_qualification;
	private String name;
	private String cpfOrCnpj;
	private BigDecimal salary;
	private SectorEnum sector;
	private String observation;

	public DtoClass_FullTimeEmployeeRegistryOutput(PersonEntity person, FullTimeEmployeePersonQualification employee) {
		this.specified_qualification = "MANAGER";
		this.name = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.salary = employee.getMonthlySalary();
		this.sector = employee.getSector();
		this.observation = employee.getObservation();
	}
}
