package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(ACCOUNTANT)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountantPersonQualification extends PersonQualificationSuperclassEntity{
    private BigDecimal monthlyCost;

    public AccountantPersonQualification (AccountantPersonQualification oldQualification) {
        super(oldQualification);
        this.monthlyCost = oldQualification.getMonthlyCost();
    }
    
    public AccountantPersonQualification (
    		PersonQualificationSuperclassEntity personQualificationSuperclassEntity,
    		BigDecimal monthlyCost) {
    	super(personQualificationSuperclassEntity);
    	this.monthlyCost = monthlyCost;
    }
}
