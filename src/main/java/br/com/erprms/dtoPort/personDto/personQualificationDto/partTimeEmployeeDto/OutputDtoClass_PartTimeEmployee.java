package br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputDtoClass_PartTimeEmployee implements PersonQualificationOutputDtoInterface{
	private String specifiedQualification;
	private String personName;
	private String cpfOrCnpj;
	private String observation;
	private String professionalRegistry;
	
	private BigDecimal hourlyRate;
	private SectorEnum sector;

	public OutputDtoClass_PartTimeEmployee(
			PersonEntity person, 
			InputDtoClass_PartTimeEmployee employee, 
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.personName = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.observation = employee.getObservation();
		this.professionalRegistry = employee.getProfessionalRegistry();
		this.hourlyRate = employee.getHourlyRate();
		this.sector = employee.getSector();
	}
}
