package br.com.erprms.serviceApplication.personService;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StatusPerson_Setter {
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	
	public StatusPerson_Setter (
			PersonRepository personRepository,
			PersonQualificationRepository personQualificationRepository) {
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
	}
	
	public void setStatusOfUse(PersonEntity person) {
		person.setStatusPersonEnum(StatusPersonalUsedEnum.USED);
		personRepository.save(person);
	}
	
	public void setSatusOfNonUse(PersonEntity person) {
		if(!personQualificationRepository.existsPersonQualificationByFinalDateIsNullAndPerson(person)) {
			person.setStatusPersonEnum(StatusPersonalUsedEnum.NOT_USED);
			personRepository.save(person);
		}
	}
}
