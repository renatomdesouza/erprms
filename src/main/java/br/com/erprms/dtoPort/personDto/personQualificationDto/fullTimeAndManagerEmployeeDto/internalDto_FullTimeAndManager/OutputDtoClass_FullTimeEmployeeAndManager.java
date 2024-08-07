package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputDtoClass_FullTimeEmployeeAndManager implements PersonQualificationOutputDtoInterface {
	private String specifiedQualification;
	private String personName;
	private Long cpfOrCnpj;
	private String observation;
	private String professionalRegistry;
	
	private BigDecimal monthlySalary;
	private SectorEnum sector;

	public OutputDtoClass_FullTimeEmployeeAndManager(
			PersonEntity person, 
			InputDtoClass_FullTimeEmployeeAndManager employee,
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.personName = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.observation = employee.getObservation();
		this.professionalRegistry = employee.getProfessionalRegistry();
		this.monthlySalary = employee.getMonthlySalary();
		this.sector = employee.getSector();
	}
}
