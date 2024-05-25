package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.personDomain.PersonsManagement_Entity;

public interface PersonsManagementRepository extends JpaRepository<PersonsManagement_Entity, Long > {

}
