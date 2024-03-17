package br.com.erprms.dtoPort.personDto.managerDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.employeePersonQualificatorInheritor.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClassToOutputManagerOfRegistry {
	private String specified_qualification;
	private String name;
	private String cpfOrCnpj;
	private BigDecimal salary;
	private SectorEnum sector;
	private String observation;

	public DtoClassToOutputManagerOfRegistry(PersonEntity person, ManagerEmployeePersonQualificationSubclass manager) {
		this.specified_qualification = "MANAGER";
		this.name = person.getFullNameOrEntityName();
		this.cpfOrCnpj = person.getCpfOrCnpj();
		this.salary = manager.getSalary();
		this.sector = manager.getSector();
		this.observation = manager.getObservation();
	}
}
