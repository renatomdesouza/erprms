package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoClass_FullTimeEmployeeAndManager;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputDtoClass_FullTimeEmployeeAndManager {
	private String specifiedQualification;
	private String name;
	private String cpfOrCnpj;
	private String observation;
	private String professionalRegistry;
	
	private BigDecimal monthlySalary;
	private SectorEnum sector;

	public OutputDtoClass_FullTimeEmployeeAndManager(
			PersonEntity person, 
			InputDtoClass_FullTimeEmployeeAndManager employee, 
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.name = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.observation = employee.getObservation();
		this.professionalRegistry = employee.getProfessionalRegistry();
		this.monthlySalary = employee.getMonthlySalary();
		this.sector = employee.getSector();
	}

	public OutputDtoClass_FullTimeEmployeeAndManager(	
			PersonQualificationSuperclassEntity managerOrEmployeeToDelete,
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.name = managerOrEmployeeToDelete.getPerson().getFullNameOrEntityName();
		this.cpfOrCnpj = managerOrEmployeeToDelete.getPerson().getCpfOrCnpj();
		this.observation = managerOrEmployeeToDelete.getObservation();
	}
}
