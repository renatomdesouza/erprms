package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;


import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;

import java.net.URI;
import java.time.LocalDate;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.InputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.OutputDtoClass_Provider;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ClientPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.OutputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.InputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.OutputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.OutputDtoClass_PartTimeEmployee;
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
		exceptionService.mismatchExceptionBetweenQualifications(personQualificationInputDto.getPerson_Id(), specifiedQualification);

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
			case ACCOUNTANT -> {
				personQualification = mapper.map(personQualificationInputDto, AccountantPersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_Accountant(
						person,
						(InputDtoClass_Accountant) personQualificationInputDto,
						specifiedQualification); break;}
			case CLIENT -> {
				personQualification = mapper.map(personQualificationInputDto, ClientPersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_Client(
						person,
						(InputDtoClass_Client) personQualificationInputDto,
						specifiedQualification); break;}
			case PROVIDER -> {
				personQualification = mapper.map(personQualificationInputDto, ProviderPersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_Provider(
						person,
						(InputDtoClass_Provider) personQualificationInputDto,
						specifiedQualification); break;}
			default -> { throw new ResponseStatusException( HttpStatus.INSUFFICIENT_STORAGE,
															"personal qualifications not provided for in the role");}
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
