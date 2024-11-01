package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;


import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ClientPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.OutputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.InputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.OutputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.OutputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.InputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.OutputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.InputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.OutputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticatedUsername;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpPost {
	private final ModelMapper mapper;
	private final PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final AuthenticatedUsername authenticationFacade;
	
	public PersonQualificationService_HttpPost(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			AuthenticatedUsername authenticationFacade) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.authenticationFacade = authenticationFacade;
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked" })
	public <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface> 
	DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> /*ResponseEntity<? extends PersonQualificationOutputDtoInterface>*/ 
	registerService(T personQualificationInputDto,
					UriComponentsBuilder uriComponentsBuilder,
					String specifiedQualification) 
			throws ResponseStatusException {
		boolean existsPerson = personRepository.existsById(personQualificationInputDto.getPerson_Id());
		new PersonQualificationExceptions(personRepository, personQualificationRepository).exceptionForPersonWhoDoesNotExist_02(existsPerson);
		
		boolean existsMismatch = mismatchBetweenQualifications(personQualificationInputDto.getPerson_Id(), specifiedQualification); 
		new PersonQualificationExceptions(personRepository, personQualificationRepository).mismatchExceptionBetweenQualifications_02(existsMismatch);
		
//		exceptionService.mismatchExceptionBetweenQualifications(personQualificationInputDto.getPerson_Id(), specifiedQualification);

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
			case RESPONSIBLE_FOR_LEGAL_PERSON -> {
				personQualification = mapper.map(personQualificationInputDto, ResponsibleForLegalPersonQualification.class);
				personQualificationOutputDto = (U) new OutputDtoClass_ResponsibleForLegalPerson(
						person,
						(InputDtoClass_ResponsibleForLegalPerson) personQualificationInputDto,
						specifiedQualification); break;}
		}

		PersonQualificationSuperclassEntity personQualificationConfigured = 
							personQualificationConfigure(person, personQualification);

		personQualificationRepository.save(personQualificationConfigured);
		
		person.setStatusPersonEnum(StatusPersonalUsedEnum.USED);
		personRepository.save(person);
//		statusPerson_Setter.setStatusOfUse(person);
		
		URI uri = new PersonQualification_CreateUri().uriCreator(	uriComponentsBuilder, 
																	specifiedQualification, 
																	person.getId());
		
		DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> dtoRecord_ServicePersonQualification = 
				new DtoRecord_ServicePersonQualification<>(uri, personQualificationOutputDto);

		return dtoRecord_ServicePersonQualification;
	}

	protected PersonQualificationSuperclassEntity personQualificationConfigure(
					PersonEntity person,
					PersonQualificationSuperclassEntity personQualification) {
		personQualification.setIsActual(true);
		personQualification.setPerson(person);
		personQualification.setInitialDate(nowSetter());
		personQualification.setHttpVerb(HttpVerbEnum.POST);
		personQualification.setLoginUser(authenticationFacade.getAuthenticatedUsername());
		
		return personQualification;
	}
	
	protected LocalDateTime nowSetter() {
		return LocalDateTime.now();
	}
	
	protected boolean mismatchBetweenQualifications(@NonNull Long id_Person, String specifiedQualification) {
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
		
		return mismatchQualificationOptional.isPresent();
	}
}
