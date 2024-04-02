package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class InputDtoClass_PartTimeEmployee {
	private Long person_Id;
	private BigDecimal hourlyRate;
	private SectorEnum sector;
	private String observation;
	private String professionalRegistry;
	
	public InputDtoClass_PartTimeEmployee(InputDtoRecord_PartTimeEmployee partyTimeEmployee) {
		this.person_Id = partyTimeEmployee.person_Id();
		this.hourlyRate = partyTimeEmployee.hourlyRate();
		this.sector = partyTimeEmployee.sector();
		this.observation = partyTimeEmployee.observation();
		this.professionalRegistry = partyTimeEmployee.professionalRegistry();
	}
}
