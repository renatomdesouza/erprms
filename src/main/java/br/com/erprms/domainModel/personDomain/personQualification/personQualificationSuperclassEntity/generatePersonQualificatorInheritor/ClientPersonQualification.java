package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class ClientPersonQualification extends PersonQualificationSuperclassEntity{
}
