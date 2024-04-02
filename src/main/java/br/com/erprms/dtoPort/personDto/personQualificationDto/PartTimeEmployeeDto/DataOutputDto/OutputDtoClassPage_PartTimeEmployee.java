package br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputDtoClassPage_PartTimeEmployee {
	private Long id;
	private String specifiedQualification;
	private Long personId;
	private String personName;
	private String personCpfOrCnpj;
	private SectorEnum sector;
	private BigDecimal hourlyRate;
	private String observation;
	private String professionalRegistry;
	private LocalDate initialDate;
	private LocalDate finalDate;
}
