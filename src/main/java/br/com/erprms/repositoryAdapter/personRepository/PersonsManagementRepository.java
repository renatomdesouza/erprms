package br.com.erprms.repositoryAdapter.personRepository;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.PersonsManagement_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonsManagementRepository extends JpaRepository<PersonsManagement_Entity, Long > {

}
