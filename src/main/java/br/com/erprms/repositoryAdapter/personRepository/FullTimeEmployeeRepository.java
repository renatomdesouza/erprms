package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;

public interface FullTimeEmployeeRepository  extends JpaRepository<FullTimeEmployeePersonQualification, Long> {

	boolean existsFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
//	@Query("SELECT m FROM FullTimeEmployeePersonQualification m WHERE finalDate = null")
	Page<FullTimeEmployeePersonQualification> findEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);

}
