package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;

@Entity
@DiscriminatorValue(PROVIDER)
//@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProviderPersonQualification extends PersonQualificationSuperclassEntity{

    public ProviderPersonQualification (ProviderPersonQualification oldQualification) {
        super(oldQualification);
    }
}
