package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;


import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataInputDto.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DataOutputDto.OutputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.StatusPerson;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpPost {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	private final StatusPerson statusPerson;
	
	public PersonQualificationService_HttpPost(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			StatusPerson statusPerson) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.statusPerson = statusPerson;
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked", "null" })
	public <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface> 
	ResponseEntity<? extends PersonQualificationOutputDtoInterface> 
	registerService(T personQualificationInputDto,
					UriComponentsBuilder uriComponentsBuilder,
					String specifiedQualification) 
			throws ResponseStatusException {
		exceptionService.exceptionForPersonWhoDoesNotExist(personQualificationInputDto.getPerson_Id());
		exceptionService.mismatchExceptionBetweenQualifications(personQualificationInputDto.getPerson_Id());

		PersonEntity person = personRepository.getReferenceById(personQualificationInputDto.getPerson_Id() );

		PersonQualificationSuperclassEntity personQualification = null;
		PersonQualificationOutputDtoInterface personQualificationOutputDto = null;
		switch (specifiedQualification) {
			case MANAGER -> {
				personQualification = mapper.map(personQualificationInputDto, ManagerPersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_FullTimeEmployeeAndManager(
															person, 
															(InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto, 
															specifiedQualification); break;}
			case FULL_TIME_EMPLOYEE -> {
				personQualification = mapper.map(personQualificationInputDto, FullTimeEmployeePersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_FullTimeEmployeeAndManager(	
															person, 
															(InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto, 
															specifiedQualification); break;}
			case PART_TIME_EMPLOYEE -> {
				personQualification = mapper.map(personQualificationInputDto, PartTimeEmployeePersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_PartTimeEmployee(	
															person, 
															(InputDtoClass_PartTimeEmployee) personQualificationInputDto, 
															specifiedQualification); break;}
			default -> { exceptionService.exceptionForNullPersonalQualification(Optional.ofNullable(personQualification)); }
		}

		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification, 
										person.getId());

		personQualification.setPerson(person);
		personQualification.setInitialDate(LocalDate.now());
		
		personQualificationRepository.save(personQualification);
		statusPerson.setStatusOfUse(person);

		var dtoRecord_ServicePersonQualification = new DtoRecord_ServicePersonQualification<>(uri, personQualificationOutputDto);
			
		return ResponseEntity.created(dtoRecord_ServicePersonQualification.uri())
								.body(dtoRecord_ServicePersonQualification.dtoOfPerson());
	}
}
