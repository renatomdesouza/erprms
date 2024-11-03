package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(CLIENT)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientPersonQualification extends PersonQualificationSuperclassEntity{
    private Integer creditTerms;

    public ClientPersonQualification (ClientPersonQualification oldQualification) {
        super(oldQualification);
        this.creditTerms = oldQualification.getCreditTerms();
    }
    
    public ClientPersonQualification (
    		PersonQualificationSuperclassEntity personQualificationSuperclassEntity, 
			Integer creditTerms) {
        super(personQualificationSuperclassEntity);
        this.creditTerms = creditTerms;
    }
}
