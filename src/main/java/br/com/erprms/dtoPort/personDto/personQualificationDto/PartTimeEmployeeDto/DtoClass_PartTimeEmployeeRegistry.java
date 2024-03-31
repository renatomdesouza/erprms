package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClass_PartTimeEmployeeRegistry {
	private Long person_Id;
	private BigDecimal hourlyRate;
	private SectorEnum sector;
	private String observation;
	private String professionalRegistry;
	
	public DtoClass_PartTimeEmployeeRegistry(DtoRecord_PartTimeEmployeeRegistry partyTimeEmployee) {
		this.person_Id = partyTimeEmployee.person_Id();
		this.hourlyRate = partyTimeEmployee.hourlyRate();
		this.sector = partyTimeEmployee.sector();
		this.observation = partyTimeEmployee.observation();
		this.professionalRegistry = partyTimeEmployee.professionalRegistry();
	}
}
