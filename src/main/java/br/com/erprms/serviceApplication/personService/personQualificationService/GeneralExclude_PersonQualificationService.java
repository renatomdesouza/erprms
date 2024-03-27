package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import jakarta.transaction.Transactional;

@Service
public class GeneralExclude_PersonQualificationService {
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonQualification_CreateUri createUri;
	private final PersonQualification_CreateManagerAndEmployeeDto createDto;
	
	public GeneralExclude_PersonQualificationService(
			PersonQualificationRepository personQualificationRepository,
			PersonQualification_CreateUri createUri,
			PersonQualification_CreateManagerAndEmployeeDto createDto) {
		this.personQualificationRepository = personQualificationRepository;
		this.createUri = createUri;
		this.createDto = createDto;
	}

	@Transactional
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri generalExclude(
					Long person_Id, 
					String specifiedQualification, 
					UriComponentsBuilder uriComponentsBuilder) {
		
		try {	
			var managerToDelete = personQualificationRepository.personActiveQualification(person_Id, specifiedQualification); 
			
			var dtoClass_ManagerAndFullTimeEmployeeRegistryOutput = 
					createDto.createManagerAndEmployeeDto(managerToDelete, specifiedQualification);
			
			var uri = createUri.uriCreator(	
							uriComponentsBuilder, 
							specifiedQualification, 
							person_Id);

			var personAsActive = personQualificationRepository.personActiveQualification(person_Id, specifiedQualification);
		
			personAsActive.setFinalDate(LocalDate.now());
			
			personQualificationRepository.save(personAsActive);

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
