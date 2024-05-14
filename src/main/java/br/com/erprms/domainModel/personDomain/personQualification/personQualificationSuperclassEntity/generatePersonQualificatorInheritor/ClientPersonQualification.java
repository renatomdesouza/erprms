package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;

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
}
