package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

import java.net.URI;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PartTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpPut {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final ManagerRepository managerRepository;
	private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
	private final PartTimeEmployeeRepository partTimeEmployeeRepository;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	
	public PersonQualificationService_HttpPut(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			ManagerRepository managerRepository,
			FullTimeEmployeeRepository fullTimeEmployeeRepository,
			PartTimeEmployeeRepository partTimeEmployeeRepository,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
		this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
		this.partTimeEmployeeRepository = partTimeEmployeeRepository;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public <T extends PersonQualificationInputDtoInterface> ResponseEntity<PersonQualificationOutputDtoInterface> update(
				T inputDtoClass, 
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) 
			throws ResponseStatusException {
		
		exceptionService.exceptionForPersonWhoDoesNotExist(inputDtoClass.getPerson_Id());

		var person = personRepository.getReferenceById(inputDtoClass.getPerson_Id());
		
		PersonQualificationSuperclassEntity personQualification = null;  
		switch (specifiedQualification) {
			case MANAGER -> { personQualification = managerRepository 
											.findManagerPersonQualificationByFinalDateIsNullAndPerson(person); break; }
			case FULL_TIME_EMPLOYEE -> { personQualification = fullTimeEmployeeRepository
											.findFullTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person); break; }
			case PART_TIME_EMPLOYEE -> { personQualification = partTimeEmployeeRepository
											.findPartTimeEmployeePersonQualificationByFinalDateIsNullAndPerson(person); break; }
		}
	
		exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(personQualification));

		mapper.map(inputDtoClass, personQualification);
		
		PersonQualificationOutputDtoInterface personQualificationOutputDto = null;
		switch (specifiedQualification) {
			case MANAGER -> { 
				managerRepository.save((ManagerPersonQualification) personQualification);
				personQualificationOutputDto = 
						new OutputDtoClass_FullTimeEmployeeAndManager( 	person, 
																		(InputDtoClass_FullTimeEmployeeAndManager) inputDtoClass, 
																		specifiedQualification); break; }
			case FULL_TIME_EMPLOYEE -> { 
				fullTimeEmployeeRepository.save((FullTimeEmployeePersonQualification) personQualification);
				personQualificationOutputDto = 
						new OutputDtoClass_FullTimeEmployeeAndManager(	person, 
																		(InputDtoClass_FullTimeEmployeeAndManager) inputDtoClass, 
																		specifiedQualification); break; }
			case PART_TIME_EMPLOYEE -> { 
				partTimeEmployeeRepository.save((PartTimeEmployeePersonQualification) personQualification); 
				personQualificationOutputDto = 
						new OutputDtoClass_PartTimeEmployee(	person, 
																(InputDtoClass_PartTimeEmployee) inputDtoClass, 
																specifiedQualification); break; }
		}
		
		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification, 
										person.getId());
		
		var dtoRecord_ServicePersonQualification = new DtoRecord_ServicePersonQualification<>(uri, personQualificationOutputDto);
		
		return ResponseEntity
				.created(dtoRecord_ServicePersonQualification.uri())
				.body(dtoRecord_ServicePersonQualification.dtoOfPerson());
	}
}
