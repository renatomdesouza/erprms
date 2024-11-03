package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(RESPONSIBLE_FOR_LEGAL_PERSON)
//@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponsibleForLegalPersonQualification extends PersonQualificationSuperclassEntity{

    public ResponsibleForLegalPersonQualification (ResponsibleForLegalPersonQualification oldQualification) {
        super(oldQualification);
    }
    
    public ResponsibleForLegalPersonQualification (PersonQualificationSuperclassEntity personQualificationSuperclassEntity) {
        super(personQualificationSuperclassEntity);
    }
}
