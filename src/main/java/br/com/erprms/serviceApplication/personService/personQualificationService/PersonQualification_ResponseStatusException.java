package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

@Service
public class PersonQualification_ResponseStatusException {
	private PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	
	public PersonQualification_ResponseStatusException(
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository) {
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
	}

	public void mismatchExceptionBetweenQualifications(@NonNull Long id_Person) {
		String qualification = personQualificationRepository.activeIncompatibleQualification(id_Person);
		
		Optional<String> qualificationOptional = Optional.ofNullable(qualification);
		
		if(qualificationOptional.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
						"A person can only be a manager, a regular Employee or a Part-Time employee. " 
						+	"Active qualification: " + qualificationOptional.get());
		}
	}

	public void exceptionForPersonWhoDoesNotExist(@NonNull Long person_Id) {
		if (!personRepository.existsById(person_Id))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
	}
}
