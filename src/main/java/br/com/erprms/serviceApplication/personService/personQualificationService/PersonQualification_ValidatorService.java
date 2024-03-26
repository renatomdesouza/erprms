package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;

@Service
public class PersonQualification_ValidatorService {
	private PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	
	public PersonQualification_ValidatorService(
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository) {
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
	}
	
public void registerServiceValidation(@NonNull Long id_Person, String specifiedQualification) {
		
		if (!personRepository.existsById(id_Person))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		Optional<PersonQualificationSuperclassEntity> fullTimeEmployeeOptional = Optional.ofNullable(
				personQualificationRepository.personActiveQualification(id_Person, specifiedQualification));
		if (!fullTimeEmployeeOptional.isEmpty()) 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is an active \"FullTimeEmployee\" registry for this Person");
	}
	
	
}
