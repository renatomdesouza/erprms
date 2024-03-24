package br.com.erprms.repositoryAdapter.personRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;


public interface ManagerRepository extends JpaRepository<ManagerPersonQualification, Long> {
	
	boolean existsManagerEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	Page<ManagerPersonQualification>findEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
	
//	@Query("SELECT m FROM ManagerEmployeePersonQualification m WHERE m.finalDate is null ORDER BY m.person.fullNameOrEntityName")
	Page<DtoClass_FullTimeEmployeeListing> findEmployeePersonQualificationByFinalDateIsNullOrderByPersonFullNameOrEntityName(
			Pageable qualificationPageable);
}
