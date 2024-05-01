package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;

import java.time.LocalDate;
import java.util.Optional;

import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.OutputExcludeDto_Accountant;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.OutPutExcludeDto_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.OutputDtoClassExclude_PartTimeEmployee;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPerson;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpDelete {
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	private final StatusPerson statusPersonOfQualification;
	
	public PersonQualificationService_HttpDelete(
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			StatusPerson statusPersonOfQualification) {
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}
	
	@Transactional
	public ResponseEntity<PersonQualificationOutputDtoInterface> exclude(
				@NonNull Long person_Id, 
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) 
				throws ResponseStatusException {
		
			PersonQualificationSuperclassEntity personQualification = 
					personQualificationRepository.personActiveQualification(person_Id, specifiedQualification);
			
			exceptionService.nonExistentQualification(Optional.ofNullable(personQualification));
			
			personQualification.setFinalDate(LocalDate.now());
			
			personQualificationRepository.save(personQualification);
			
			var person = personRepository.getReferenceById(person_Id);

			statusPersonOfQualification.setSatusOfNonUse(person);
			
			var uri = createUri.uriCreator(	uriComponentsBuilder, 
											specifiedQualification, 
											person_Id);

			PersonQualificationOutputDtoInterface outPutExcludeDto = null;
			switch (specifiedQualification) {
				case MANAGER -> { outPutExcludeDto = 
								new OutPutExcludeDto_FullTimeEmployeeAndManager(	personQualification, 
																					specifiedQualification); break; }
				case FULL_TIME_EMPLOYEE -> { outPutExcludeDto =
						new OutPutExcludeDto_FullTimeEmployeeAndManager(	personQualification, 
																			specifiedQualification); break; }
				case PART_TIME_EMPLOYEE -> { outPutExcludeDto = 
								new OutputDtoClassExclude_PartTimeEmployee(	personQualification, 
																			specifiedQualification); break; }
				case ACCOUNTANT -> { outPutExcludeDto =
						new OutputExcludeDto_Accountant(	personQualification,
															specifiedQualification); break; }
			}
			
			var dtoRecord_ServicePersonQualification = 
					new DtoRecord_ServicePersonQualification<>(uri, outPutExcludeDto);
			
			return ResponseEntity
					.created(dtoRecord_ServicePersonQualification.uri())
					.body(dtoRecord_ServicePersonQualification.dtoOfPerson());

	}
}
