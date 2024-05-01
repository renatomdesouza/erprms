package br.com.erprms.repositoryAdapter.personRepository;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountantRepository  extends JpaRepository<AccountantPersonQualification, Long> {

    AccountantPersonQualification findAccountantPersonQualificationByFinalDateIsNullAndPerson(PersonEntity person);

    Page<AccountantPersonQualification> findAccountantPersonQualificationByFinalDateIsNull(Pageable qualificationPageable);
}
