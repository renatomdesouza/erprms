package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DtoClass_ManagerAndFullTimeEmployeeRegistryOutput {
	private String specifiedQualification;
	private String name;
	private String cpfOrCnpj;
	private String observation;
	private String professionalRegistry;
	
	private BigDecimal MonthlySalary;
	private SectorEnum sector;

	public DtoClass_ManagerAndFullTimeEmployeeRegistryOutput(
			PersonEntity person, 
			DtoClass_ManagerAndFullTimeEmployeeRegistry employee, 
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.name = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.observation = employee.getObservation();
		this.professionalRegistry = employee.getProfessionalRegistry();
		this.MonthlySalary = employee.getMonthlySalary();
		this.sector = employee.getSector();
	}

	public DtoClass_ManagerAndFullTimeEmployeeRegistryOutput(	PersonQualificationSuperclassEntity managerOrEmployeeToDelete,
																String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.name = managerOrEmployeeToDelete.getPerson().getFullNameOrEntityName();
		this.cpfOrCnpj = managerOrEmployeeToDelete.getPerson().getCpfOrCnpj();
		this.observation = managerOrEmployeeToDelete.getObservation();
	}
}
