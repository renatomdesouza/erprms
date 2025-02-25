package br.com.erprms.infrastructure.exceptionManager.responseStatusException;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;


@Service
public class PersonQualificationExceptions {
	private final PersonQualificationRepository personQualificationRepository;
	
	public PersonQualificationExceptions(PersonQualificationRepository personQualificationRepository) {
		this.personQualificationRepository = personQualificationRepository;
	}

	public void mismatchExceptionBetweenQualifications(@NonNull Long id_Person, String specifiedQualification) {
		String mismatchQualification = null;

		if(specifiedQualification.equals(MANAGER) ||
			specifiedQualification.equals(FULL_TIME_EMPLOYEE) ||
			specifiedQualification.equals(PART_TIME_EMPLOYEE))
				mismatchQualification = personQualificationRepository.multipleQualificationIncompatibilities(id_Person);

		if(specifiedQualification.equals(CLIENT) ||
			specifiedQualification.equals(ACCOUNTANT) ||
			specifiedQualification.equals(RESPONSIBLE_FOR_LEGAL_PERSON) ||
			specifiedQualification.equals(PROVIDER))
				mismatchQualification = personQualificationRepository.individualQualificationIncompatibilities(id_Person, specifiedQualification);

		Optional<String> mismatchQualificationOptional = Optional.ofNullable(mismatchQualification);

		if(mismatchQualificationOptional.isPresent()) {
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, """
						A person can only be a Manager, a regular Employee or a Part-Time employee,
						and still cannot have the same active qualification.
						Active qualification:
					""" + mismatchQualificationOptional.get());
		}
	}
	
	public void mismatchExceptionBetweenQualifications_02(boolean existsMismatch) {
		if(existsMismatch) {
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE,	"A person can only be a Manager, a regular Employee or a Part-Time employee, "
														+ "and still cannot have the same active qualification.");
		}
	}

	public void exceptionForPersonWhoDoesNotExist(boolean notExistsPerson) {
		if (notExistsPerson)
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
	}
	
	public void exceptionForQualifiedPersonWhoDoesNotExist(boolean notExistsPersonQualification) {
		if (notExistsPersonQualification) 
			throw new ResponseStatusException(	HttpStatus.INSUFFICIENT_STORAGE, 
												"This person does not have this qualification in the database");
	}
	
	public void exceptionForNullPersonalQualification(Optional<PersonQualificationSuperclassEntity> personQualificationOptional) {
		if (personQualificationOptional.isEmpty()) 
			throw new ResponseStatusException(	HttpStatus.INSUFFICIENT_STORAGE, 
												"The personal qualification variable cannot be null");
	}
	
	public void nonExistentQualification(Optional<PersonQualificationSuperclassEntity> personQualificationOptional) {
		if (personQualificationOptional.isEmpty()) 
			throw new ResponseStatusException(	HttpStatus.INSUFFICIENT_STORAGE, 
												"There is no such qualification to be deleted");
	}
	
}
