package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClass_ManagerAndFullTimeEmployeeRegistryOutput /*<T extends EmployeeInterface>*/ {
	private String specifiedQualification;
	private String name;
	private String cpfOrCnpj;
	private BigDecimal monthlySalary;
	private SectorEnum sector;
	private String observation;

	public DtoClass_ManagerAndFullTimeEmployeeRegistryOutput(
			PersonEntity person, 
			DtoClass_ManagerAndFullTimeEmployeeRegistry employee, 
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.name = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.monthlySalary = employee.getMonthlySalary();
		this.sector = employee.getSector();
		this.observation = employee.getObservation();
	}
}
