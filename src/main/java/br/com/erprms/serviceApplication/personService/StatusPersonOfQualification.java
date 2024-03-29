package br.com.erprms.serviceApplication.personService;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StatusPersonOfQualification {
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	
	public StatusPersonOfQualification (
			PersonRepository personRepository,
			PersonQualificationRepository personQualificationRepository) {
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
	}
	
	public void setStatusUser(PersonEntity person) {
		person.setStatusPersonEnum(StatusPersonalUseEnum.USED);
		personRepository.save(person);
	}
	
	public void setSatusNotUser(PersonEntity person) {
		if(!personQualificationRepository.existsFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person)) {
			person.setStatusPersonEnum(StatusPersonalUseEnum.NOT_USED);
			personRepository.save(person);
		}
	}
}
