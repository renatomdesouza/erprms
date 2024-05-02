package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;

@Entity
@DiscriminatorValue(PROVIDER)
public class ProviderPersonQualification extends PersonQualificationSuperclassEntity{
}
