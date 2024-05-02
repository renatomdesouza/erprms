package br.com.erprms.repositoryAdapter.personRepository;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsibleForLegalPersonRepository extends JpaRepository<ResponsibleForLegalPersonQualification, Long> {

    ResponsibleForLegalPersonQualification findResponsibleForLegalPersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);

    Page<ResponsibleForLegalPersonQualification> findResponsibleForLegalPersonQualificationByFinalDateIsNull(Pageable qualificationPageable);

}
