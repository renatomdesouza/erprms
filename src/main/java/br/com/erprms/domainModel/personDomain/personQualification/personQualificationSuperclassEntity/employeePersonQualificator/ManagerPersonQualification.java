package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.EmployeePersonQualificatorInterface;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(MANAGER)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ManagerPersonQualification 
					extends PersonQualificationSuperclassEntity 
					implements FullTimeEmployeePersonQualificatorInterface, EmployeePersonQualificatorInterface {

	@Enumerated(EnumType.STRING) 
	private SectorEnum sector;
	
	private BigDecimal monthlySalary;

	public ManagerPersonQualification (ManagerPersonQualification oldQualification) {
		super(oldQualification);
		this.sector = oldQualification.getSector();
		this.monthlySalary = oldQualification.getMonthlySalary();
	}
	
	public ManagerPersonQualification (
			PersonQualificationSuperclassEntity personQualificationSuperclassEntity, 
			SectorEnum sector, 
			BigDecimal monthlySalary ) {
		super(personQualificationSuperclassEntity);
		this.sector = sector;
		this.monthlySalary = monthlySalary;
	}
}
