package br.com.erprms.repositoryAdapter.personRepository;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderPersonQualification, Long> {

    ProviderPersonQualification findProviderPersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);

    Page<ProviderPersonQualification> findProviderPersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
}
