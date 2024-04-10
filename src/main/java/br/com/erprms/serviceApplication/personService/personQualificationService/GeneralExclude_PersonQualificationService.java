package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.ResponseEntityOutputDtoExclude_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutPutExcludeDto_FullTimeEmployeeAndManager;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPersonOfQualification;
import jakarta.transaction.Transactional;

@Service
public class GeneralExclude_PersonQualificationService {
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonRepository personRepository;
	private final PersonQualification_CreateUri createUri;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public GeneralExclude_PersonQualificationService(
			PersonQualificationRepository personQualificationRepository,
			PersonRepository personRepository,
			PersonQualification_CreateUri createUri,
			PersonQualification_ResponseStatusException exceptionService,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.personQualificationRepository = personQualificationRepository;
		this.personRepository = personRepository;
		this.createUri = createUri;
		this.exceptionService = exceptionService;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}

	@SuppressWarnings("null")
	@Transactional
	public ResponseEntity<OutPutExcludeDto_FullTimeEmployeeAndManager> generalExclude(
					@NonNull Long person_Id, 
					UriComponentsBuilder uriComponentsBuilder, 
					String specifiedQualification) {
		
		exceptionService.exceptionForPersonWhoDoesNotExist(person_Id);
		
		try {	
			var personQualificationToDelete = personQualificationRepository.personActiveQualification(person_Id, specifiedQualification); 
			
			var personAsActive = personQualificationRepository.personActiveQualification(person_Id, specifiedQualification);
			personAsActive.setFinalDate(LocalDate.now());
			personQualificationRepository.save(personAsActive);
			
			@SuppressWarnings("null")
			var person = personRepository.getReferenceById(person_Id);
			statusPersonOfQualification.setSatusNotUser(person);
			
			URI uri = createUri.uriCreator(	uriComponentsBuilder, 
											specifiedQualification, 
											person_Id);

			var outPutExcludeDto_FullTimeEmployeeAndManager = 
					new OutPutExcludeDto_FullTimeEmployeeAndManager(	personQualificationToDelete, 
																	specifiedQualification);
			
			var responseEntityOutputDto_FullTimeEmployeeAndManager =
					new ResponseEntityOutputDtoExclude_FullTimeEmployeeAndManager(
										outPutExcludeDto_FullTimeEmployeeAndManager,
										uri);
			
			return ResponseEntity
					.created(responseEntityOutputDto_FullTimeEmployeeAndManager.uri())
					.body(responseEntityOutputDto_FullTimeEmployeeAndManager.outPutExcludeDto_FullTimeEmployeeAndManager());

		} catch (NullPointerException ex) { 
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no such qualification to be deleted");
		}
	}
}
