package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.employeePersonQualificatorInheritor.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToListingOfQualification;


public interface ManagerRepository extends JpaRepository<ManagerEmployeePersonQualificationSubclass, Long> {
	
	boolean existsEmployeePersonQualificationSubclassByFinalDateIsNullAndPerson(PersonEntity person);
	
	@Query("SELECT m FROM ManagerEmployeePersonQualificationSubclass m WHERE m.finalDate is null")
	Page<ManagerEmployeePersonQualificationSubclass> findManagerActive(Pageable qualificationPageable);
	/* Equivalent Derived Query ==> */ Page<ManagerEmployeePersonQualificationSubclass>findEmployeePersonQualificationSubclassByFinalDateIsNull(Pageable qualificationPageable);
	
//	@Query("SELECT m FROM ManagerEmployeePersonQualificationSubclass m WHERE m.finalDate is null ORDER BY m.person.fullNameOrEntityName")
	Page<DtoClassToListingOfQualification> findEmployeePersonQualificationSubclassByFinalDateIsNullOrderByPersonFullNameOrEntityName(
			Pageable qualificationPageable);

}
