package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClassPage_FullTimeEmployeeAndManager;

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
								AND p.is_actual IS TRUE 
								AND specified_qualification = :specified_qualification 
					LIMIT 1 
			""", nativeQuery = true)
	PersonQualificationSuperclassEntity personActiveQualification(Long person_id, String specified_qualification);


	@Query(	value = """
			SELECT p.specified_qualification
				FROM person_qualification p
					WHERE
						p.person_id = :person_id
						AND p.final_date IS NULL
						AND p.is_actual IS TRUE
						AND (
								p.specified_qualification = 'manager'
								OR
								p.specified_qualification = 'full-time-employee'
								OR
								p.specified_qualification = 'part-time-employee'
							)
				LIMIT 1
			""", nativeQuery = true)
	String multipleQualificationIncompatibilities(Long person_id);

	@Query(	value = """
			SELECT p.specified_qualification
				FROM person_qualification p
					WHERE
						p.person_id = :person_id
						AND p.final_date IS NULL
						AND p.is_actual IS TRUE
						AND p.specified_qualification = :mismatchQualification
				LIMIT 1
			""", nativeQuery = true)
	String individualQualificationIncompatibilities(Long person_id, String mismatchQualification);

	boolean existsPersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);
	//existsFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson
	
	@Query(value = "SELECT * FROM person_qualification p WHERE p.final_date IS NULL",
			nativeQuery = true)
	Page<OutputDtoClassPage_FullTimeEmployeeAndManager> findEmployeePersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
	
	
	@Query(value = """
				SELECT * 
					FROM person_qualification p 
						WHERE 
							p.final_date IS NULL
							AND p.specified_qualification = :specified 
			""", nativeQuery = true)
	Page<OutputDtoClassPage_FullTimeEmployeeAndManager> findPersonQualificationPage(Pageable qualificationPageable, String specified);
}
