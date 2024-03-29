package br.com.erprms.serviceApplication.personService.personQualificationService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

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
	
public void exceptionRegisterServiceForManagerAndFullTimeEmployee(@NonNull Long id_Person, String specifiedQualification) {
		
		if (!personRepository.existsById(id_Person))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
			
		var managerOptional = 
				Optional.ofNullable(
						personQualificationRepository.personActiveQualification(id_Person, MANAGER));
		var employeeOptional = 
				Optional.ofNullable(
						personQualificationRepository.personActiveQualification(id_Person, FULL_TIME_EMPLOYEE));
		
		if(	(specifiedQualification.contains(MANAGER) && employeeOptional.isPresent() ) 
			||
			(specifiedQualification.contains(FULL_TIME_EMPLOYEE) && managerOptional.isPresent() ) ) 
				throw new ResponseStatusException(
						HttpStatus.INSUFFICIENT_STORAGE, 
						"A person cannot be both a Manager and an ordinary Employee");
		
		if(	(specifiedQualification.contains(MANAGER) && managerOptional.isPresent() ) 
			||
			(specifiedQualification.contains(FULL_TIME_EMPLOYEE) && employeeOptional.isPresent() ) ) 
				throw new ResponseStatusException(
						HttpStatus.INSUFFICIENT_STORAGE, 
						"There is already an active qualification for this role");
	}

	public void registerServiceExceptionToUpdateAndExclude(@NonNull Long person_Id, String specifiedQualification) {
		if (!personRepository.existsById(person_Id))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
	}
}
