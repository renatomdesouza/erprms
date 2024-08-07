package br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.InputDtoRecord_PartTimeEmployee;
import lombok.Getter;

@Getter
public class InputDtoClass_PartTimeEmployee implements PersonQualificationInputDtoInterface{
	private Long person_Id;
	private BigDecimal hourlyRate;
	private SectorEnum sector;
	private String observation;
	private String professionalRegistry;
	
	public InputDtoClass_PartTimeEmployee(InputDtoRecord_PartTimeEmployee partyTimeEmployee) {
		this.person_Id =
				Long.parseLong(partyTimeEmployee.person_Id());

		this.hourlyRate =
				BigDecimal.valueOf(
						Long.parseLong(partyTimeEmployee.hourlyRate())) ;

		this.sector =
				SectorEnum.valueOf(partyTimeEmployee.sector());

		this.observation = partyTimeEmployee.observation();
		this.professionalRegistry = partyTimeEmployee.professionalRegistry();
	}
}
