package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputDtoClassPage_FullTimeEmployeeAndManager implements PersonQualificationOutputDtoInterface{
	private Long id;
	private String specifiedQualification;
	private Long personId;
	private String personName;
	private String cpfOrCnpj;
	private SectorEnum sector;
	private BigDecimal salary;
	private String observation;
	private String professionalRegistry;
	private LocalDateTime initialDate;
	private LocalDateTime finalDate;
}
