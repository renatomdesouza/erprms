package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import jakarta.transaction.Transactional;

@Service
public class GeneralExclude_PersonQualificationService {
	private final PersonQualificationRepository personQualificationRepository;
	
	public GeneralExclude_PersonQualificationService(PersonQualificationRepository personQualificationRepository) {
		this.personQualificationRepository = personQualificationRepository;
	}

	@Transactional
	public void generalExclude(Long person_Id, String specifiedQualification) {
		
		try {	
			var personAsActiveManager = personQualificationRepository.personActiveQualification(person_Id, "MANAGER");
		
			personAsActiveManager.setFinalDate(LocalDate.now());
			
			personQualificationRepository.save(personAsActiveManager);
		
		} catch (NullPointerException ex) { 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no such qualification to be deleted");
		}
	}
}
