package br.com.erprms.serviceApplication.personService.personQualificationService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.*;
//import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

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
	
	public void mismatchExceptionBetweenManagerAndEmployees(@NonNull Long id_Person, String specifiedQualification) {
		var managerOptional = 
				Optional.ofNullable(
						personQualificationRepository.personActiveQualification(id_Person, MANAGER));
		var employeeOptional = 
				Optional.ofNullable(
						personQualificationRepository.personActiveQualification(id_Person, FULL_TIME_EMPLOYEE));
		var partTimeEmployeeOptional = 
				Optional.ofNullable(
						personQualificationRepository.personActiveQualification(id_Person, PART_TIME_EMPLOYEE));
		
		if(	
			(specifiedQualification.contains(MANAGER) && 
				(employeeOptional.isPresent() || partTimeEmployeeOptional.isPresent()) ) 
			||
			(specifiedQualification.contains(FULL_TIME_EMPLOYEE) && 
				(managerOptional.isPresent() || partTimeEmployeeOptional.isPresent()) ) 
			||
			(specifiedQualification.contains(PART_TIME_EMPLOYEE) && 
				(employeeOptional.isPresent() || managerOptional.isPresent()) )
			)  {
			throw new ResponseStatusException(
						HttpStatus.INSUFFICIENT_STORAGE, 
						"A person can only be a manager, a regular Employee or a Part-Time employee");
		}
		
	}

	public void exceptionForPersonWhoDoesNotExist(@NonNull Long person_Id) {
		if (!personRepository.existsById(person_Id))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
	}
}
