package br.com.erprms.dtoPort.personDto.managerDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClassToRegistryOfManager {
	private Long person_Id;
	private BigDecimal salary;
	private SectorEnum sector;
	private String observation;
	
	public DtoClassToRegistryOfManager(DtoRecordToRegistryOfManager manager) {
		this.person_Id = manager.person_Id();
		this.salary = manager.salary();
		this.sector = manager.sector();
		this.observation = manager.observation();
	}
}
