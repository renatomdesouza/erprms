package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;

public interface PersonQualificationRepository extends JpaRepository<PersonQualificationSuperclassEntity, Long> {
	
}
