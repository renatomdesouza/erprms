package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

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
@DiscriminatorValue(PART_TIME_EMPLOYEE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PartTimeEmployeePersonQualification 
					extends PersonQualificationSuperclassEntity 
					implements EmployeePersonQualificatorInterface {
	
	@Enumerated(EnumType.STRING) 
	private SectorEnum sector;
	
	private BigDecimal hourlyRate;

	public PartTimeEmployeePersonQualification (PartTimeEmployeePersonQualification oldQualification) {
		super(oldQualification);
		this.sector = oldQualification.getSector();
		this.hourlyRate = oldQualification.getHourlyRate();
	}
	
	public PartTimeEmployeePersonQualification (
			PersonQualificationSuperclassEntity personQualificationSuperclassEntity, 
			SectorEnum sector, 
			BigDecimal hourlyRate) {
		super(personQualificationSuperclassEntity);
		this.sector = sector;
		this.hourlyRate = hourlyRate;
	}
}
