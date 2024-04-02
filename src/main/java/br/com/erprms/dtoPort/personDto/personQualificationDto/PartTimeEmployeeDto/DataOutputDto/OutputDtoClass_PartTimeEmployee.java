package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto.InputDtoClass_PartTimeEmployee;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputDtoClass_PartTimeEmployee {
	private String specifiedQualification;
	private String name;
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
		this.name = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.observation = employee.getObservation();
		this.professionalRegistry = employee.getProfessionalRegistry();
		this.hourlyRate = employee.getHourlyRate();
		this.sector = employee.getSector();
	}

	public OutputDtoClass_PartTimeEmployee(	
			PersonQualificationSuperclassEntity partyTimeEmployeeToDelete,
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.name = partyTimeEmployeeToDelete.getPerson().getFullNameOrEntityName();
		this.cpfOrCnpj = partyTimeEmployeeToDelete.getPerson().getCpfOrCnpj();
		this.observation = partyTimeEmployeeToDelete.getObservation();
	}
}
