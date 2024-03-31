package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;

public interface PartTimeEmployeeRepository extends JpaRepository<PartTimeEmployeePersonQualification, Long> { 
	
	PartTimeEmployeePersonQualification findPartTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	Page<PartTimeEmployeePersonQualification> findPartTimeEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
}
