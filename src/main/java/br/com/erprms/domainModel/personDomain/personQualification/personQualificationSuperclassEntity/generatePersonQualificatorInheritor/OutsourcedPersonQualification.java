package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OUTSOURCED")
public class OutsourcedPersonQualification extends PersonQualificationSuperclassEntity{
}
