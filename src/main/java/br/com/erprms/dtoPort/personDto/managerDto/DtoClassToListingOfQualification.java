package br.com.erprms.dtoPort.personDto.managerDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.employeePersonQualificatorInheritor.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import lombok.Getter;

@Getter
public class DtoClassToListingOfQualification {
	private Long id;
	private String specifiedQualification;
	private Long personId;
	private String personName;
	private String personCpfOrCnpj;
	private SectorEnum sector;
	private BigDecimal salary;
	private String observation;
	private String professionalRegistry;
	private LocalDate initialDate;
	private LocalDate finalDate;

	
	public DtoClassToListingOfQualification (ManagerEmployeePersonQualificationSubclass personQualification) {
		this.id = personQualification.getId();
		this.specifiedQualification = "MANAGER";
		this.personId = personQualification.getPerson().getId();
		this.personName = personQualification.getPerson().getFullNameOrEntityName();
		this.personCpfOrCnpj = personQualification.getPerson().getCpfOrCnpj();
		this.salary = personQualification.getMonthlySalary();
		this.sector = personQualification.getSector();
		this.observation = personQualification.getObservation();
		this.professionalRegistry = personQualification.getProfessionalRegistry();
		this.initialDate = personQualification.getInitialDate();
		this.finalDate = personQualification.getFinalDate();
	}
	
	
}
