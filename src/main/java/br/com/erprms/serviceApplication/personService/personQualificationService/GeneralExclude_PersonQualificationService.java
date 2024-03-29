package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPersonOfQualification;
import jakarta.transaction.Transactional;

@Service
public class GeneralExclude_PersonQualificationService {
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonRepository personRepository;
	private final PersonQualification_CreateUri createUri;
	private final PersonQualification_CreateManagerAndEmployeeDto createDto;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public GeneralExclude_PersonQualificationService(
			PersonQualificationRepository personQualificationRepository,
			PersonRepository personRepository,
			PersonQualification_CreateUri createUri,
			PersonQualification_CreateManagerAndEmployeeDto createDto,
			PersonQualification_ResponseStatusException exceptionService,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.personQualificationRepository = personQualificationRepository;
		this.personRepository = personRepository;
		this.createUri = createUri;
		this.createDto = createDto;
		this.exceptionService = exceptionService;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}

	@Transactional
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri generalExclude(
					@NonNull Long person_Id, 
					UriComponentsBuilder uriComponentsBuilder, 
					String specifiedQualification) {
		
		exceptionService.registerServiceExceptionToUpdateAndExclude(person_Id, specifiedQualification);
		
		try {	
			var personQualificationToDelete = personQualificationRepository.personActiveQualification(person_Id, specifiedQualification); 
			
			var dtoClass_ManagerAndFullTimeEmployeeRegistryOutput = 
					createDto.createManagerAndEmployeeDto(personQualificationToDelete, specifiedQualification);
			
			var personAsActive = personQualificationRepository.personActiveQualification(person_Id, specifiedQualification);
			personAsActive.setFinalDate(LocalDate.now());
			personQualificationRepository.save(personAsActive);
			
			@SuppressWarnings("null")
			var person = personRepository.getReferenceById(person_Id);
			statusPersonOfQualification.setSatusNotUser(person);
			
			var uri = createUri.uriCreator(	
							uriComponentsBuilder, 
							specifiedQualification, 
							person_Id);

			return new DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri(
										dtoClass_ManagerAndFullTimeEmployeeRegistryOutput,
										uri);

		} catch (NullPointerException ex) { 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no such qualification to be deleted");
		}
	}
}
