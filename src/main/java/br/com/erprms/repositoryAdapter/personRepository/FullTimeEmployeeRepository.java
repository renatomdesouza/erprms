package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeToListing;

public interface FullTimeEmployeeRepository  extends JpaRepository<FullTimeEmployeePersonQualification, Long> {

	boolean existsFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	Page<FullTimeEmployeePersonQualification> findEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
}
