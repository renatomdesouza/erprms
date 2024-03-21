package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;


public interface ManagerRepository extends JpaRepository<ManagerEmployeePersonQualification, Long> {
	
	boolean existsEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	@Query("SELECT m FROM ManagerEmployeePersonQualification m WHERE m.finalDate is null")
	Page<ManagerEmployeePersonQualification> findManagerActive(Pageable qualificationPageable);
	/* Equivalent Derived Query ==> */ Page<ManagerEmployeePersonQualification>findEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
	
//	@Query("SELECT m FROM ManagerEmployeePersonQualification m WHERE m.finalDate is null ORDER BY m.person.fullNameOrEntityName")
	Page<DtoClass_FullTimeEmployeeListing> findEmployeePersonQualificationByFinalDateIsNullOrderByPersonFullNameOrEntityName(
			Pageable qualificationPageable);

}
