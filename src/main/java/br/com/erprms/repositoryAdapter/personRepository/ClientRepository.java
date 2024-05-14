package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ClientPersonQualification;

public interface ClientRepository extends JpaRepository<ClientPersonQualification, Long> {

    ClientPersonQualification findClientPersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);

    ClientPersonQualification findClientPersonQualificationByIsActualIsTrueAndPerson(PersonEntity person);

    Page<ClientPersonQualification> findClientPersonQualificationByFinalDateIsNull(Pageable qualificationPageable);

    Page<ClientPersonQualification> findClientPersonQualificationByIsActualIsTrue(Pageable qualificationPageable);
}
