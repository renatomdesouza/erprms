package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;


public interface PersonRepository extends JpaRepository<PersonEntity, Long>{
	@SuppressWarnings("null")
	Page<PersonEntity> findAll(Pageable personPageable);
	
	Page<PersonEntity> findByIsNaturalPersonTrue(Pageable personPageable);
	
	Page<PersonEntity> findByIsNaturalPersonFalse(Pageable personPageable);
	
	PersonEntity findByIdAndStatusPersonEnum(Long id, StatusPersonalUseEnum statusPersonEnum);

	
	@Query(	value = """
					SELECT p.email
						FROM person p
							WHERE
								p.email = :email
					LIMIT 1
			""", nativeQuery = true)
	String findByEmail(String email);
}
