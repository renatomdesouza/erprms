package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import java.net.URI;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
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
import br.com.erprms.serviceApplication.personService.StatusPersonOfQualification;
import br.com.erprms.serviceApplication.personService.personQualificationService.PersonQualification_CreateUri;
import br.com.erprms.serviceApplication.personService.personQualificationService.PersonQualification_ResponseStatusException;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpPost {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonQualification_ResponseStatusException exceptionService;
	private final PersonQualification_CreateUri createUri;
	private final StatusPersonOfQualification statusPersonOfQualification;
	
	public PersonQualificationService_HttpPost(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			PersonQualification_ResponseStatusException exceptionService,
			PersonQualification_CreateUri createUri,
			StatusPersonOfQualification statusPersonOfQualification) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.exceptionService = exceptionService;
		this.createUri = createUri;
		this.statusPersonOfQualification = statusPersonOfQualification;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public <T extends PersonQualificationInputDtoInterface> 
	ResponseEntity<? extends PersonQualificationOutputDtoInterface> 
	registerService(T personQualificationInputDto,
					UriComponentsBuilder uriComponentsBuilder,
					String specifiedQualification) 
			throws ResponseStatusException {
			
			exceptionService.exceptionForPersonWhoDoesNotExist(personQualificationInputDto.getPerson_Id());
			exceptionService.mismatchExceptionBetweenQualifications(personQualificationInputDto.getPerson_Id());

			PartTimeEmployeePersonQualification partTimeEmployee = 
					mapper.map(	personQualificationInputDto, 
								PartTimeEmployeePersonQualification.class);
			
			PersonEntity person = 
					personRepository.getReferenceById(personQualificationInputDto.getPerson_Id() );
			
			partTimeEmployee.setPerson(person);
			partTimeEmployee.setInitialDate(LocalDate.now());

			personQualificationRepository.save(partTimeEmployee);
			statusPersonOfQualification.setStatusUser(person);

			URI uri = createUri.uriCreator(	uriComponentsBuilder, 
											specifiedQualification, 
											person.getId());
			
			PersonQualificationOutputDtoInterface personQualificationOutputDto = 
														outputDtoCreate(person,
																		personQualificationInputDto, 
																		specifiedQualification);
			
			if (personQualificationOutputDto == null) 
					throw new ResponseStatusException(	HttpStatus.INSUFFICIENT_STORAGE, 
														"The personal qualification variable cannot be null");;
				
			var dtoRecord_ServicePersonQualification =
					new DtoRecord_ServicePersonQualification<>(	uri,
																personQualificationOutputDto);
				
			return ResponseEntity.created(dtoRecord_ServicePersonQualification.uri())
									.body(dtoRecord_ServicePersonQualification.dtoOfPerson());
	}

	@SuppressWarnings("unchecked")
	private <T extends PersonQualificationInputDtoInterface, U extends PersonQualificationOutputDtoInterface> 
	U outputDtoCreate(	PersonEntity person,
						T personQualificationInputDto, 
						String specifiedQualification ) {
			
			if(personQualificationInputDto instanceof InputDtoClass_FullTimeEmployeeAndManager) 
				return (U) new OutputDtoClass_FullTimeEmployeeAndManager(
									person, 
									(InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto, 
									specifiedQualification);
			
			if(personQualificationInputDto instanceof InputDtoClass_PartTimeEmployee) 
				return (U) new OutputDtoClass_PartTimeEmployee(
									person, 
									(InputDtoClass_PartTimeEmployee) personQualificationInputDto, 
									specifiedQualification);
			
			return null;
	}
}


