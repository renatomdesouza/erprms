package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.personDomain.PersonsManagementEntity;

public interface PersonsManagementRepository extends JpaRepository<PersonsManagementEntity, Long > {

}
