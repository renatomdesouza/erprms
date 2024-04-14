package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;


public interface ManagerRepository extends JpaRepository<ManagerPersonQualification, Long> {
	
	boolean existsManagerEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	ManagerPersonQualification findManagerEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	@Query("SELECT m FROM ManagerPersonQualification m WHERE finalDate = null")
	Page<ManagerPersonQualification>findManagerPersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
	
	ManagerPersonQualification findManagerPersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);

}
