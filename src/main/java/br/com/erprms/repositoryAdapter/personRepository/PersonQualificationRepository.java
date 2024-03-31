package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;

public interface PersonQualificationRepository extends JpaRepository<PersonQualificationSuperclassEntity, Long> {
	
//	@Query("SELECT m FROM ManagerEmployeePersonQualification m WHERE m.finalDate is null")
	@Query(	value = """
					SELECT p.specified_qualification 
						FROM person_qualification p 
							WHERE p.id = ?1
			""", nativeQuery = true)
	String qualification(Long id);
	
	@Query(	value = """
					SELECT * 
						FROM person_qualification p 
							WHERE 
								p.person_id = :person_id 
								AND p.final_date IS NULL 
								AND specified_qualification = :specified_qualification 
					LIMIT 1 
			""", nativeQuery = true)
	PersonQualificationSuperclassEntity personActiveQualification(Long person_id, String specified_qualification);

	
	boolean existsFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	
	
	@Query(value = "SELECT * FROM person_qualification p WHERE p.final_date IS NULL",
			nativeQuery = true)
	Page<DtoClass_ManagerAndFullTimeEmployeeToListing> findEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
}
