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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
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
import br.com.erprms.repositoryAdapter.personRepository.AccountantRepository;
import br.com.erprms.repositoryAdapter.personRepository.ClientRepository;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PartTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.repositoryAdapter.personRepository.ProviderRepository;
import br.com.erprms.repositoryAdapter.personRepository.ResponsibleForLegalPersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpPut {
    private final ModelMapper mapper;
    private final PersonRepository personRepository;
    private final PersonQualificationRepository personQualificationRepository;
    private final ManagerRepository managerRepository;
    private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
    private final PartTimeEmployeeRepository partTimeEmployeeRepository;
    private final AccountantRepository accountantRepository;
    private final ClientRepository clientRepository;
    private final ProviderRepository providerRepository;
    private final ResponsibleForLegalPersonRepository responsibleForLegalPersonRepository;
    private final PersonQualificationExceptions exceptionService;
    private final PersonQualification_CreateUri createUri;
    private final AuthenticatedUsername authenticationFacade;

    public PersonQualificationService_HttpPut(
            ModelMapper mapper,
            PersonRepository personRepository,
            PersonQualificationRepository personQualificationRepository,
            ManagerRepository managerRepository,
            FullTimeEmployeeRepository fullTimeEmployeeRepository,
            PartTimeEmployeeRepository partTimeEmployeeRepository,
            ClientRepository clientRepository,
            ProviderRepository providerRepository,
            ResponsibleForLegalPersonRepository responsibleForLegalPersonRepository,
            AccountantRepository accountantRepository,
            AuthenticatedUsername authenticationFacade,
            PersonQualificationExceptions exceptionService,
            PersonQualification_CreateUri createUri) {
        this.mapper = mapper;
        this.personRepository = personRepository;
        this.personQualificationRepository = personQualificationRepository;
        this.managerRepository = managerRepository;
        this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
        this.partTimeEmployeeRepository = partTimeEmployeeRepository;
        this.accountantRepository = accountantRepository;
        this.clientRepository = clientRepository;
        this.providerRepository = providerRepository;
        this.responsibleForLegalPersonRepository = responsibleForLegalPersonRepository;
        this.authenticationFacade = authenticationFacade;
        this.exceptionService = exceptionService;
        this.createUri = createUri;
    }

    @Transactional
    public <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface> 
    DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> /*ResponseEntity<PersonQualificationOutputDtoInterface>*/ 
    update(
            T personQualificationInputDto,
            UriComponentsBuilder uriComponentsBuilder,
            String specifiedQualification)
        throws ResponseStatusException {

        var person = personRepository.getReferenceById(personQualificationInputDto.getPerson_Id());

        exceptionService.exceptionForPersonWhoDoesNotExist(personQualificationInputDto.getPerson_Id());
        
        PersonQualificationOutputDtoInterface personQualificationOutputDto = null;
        switch (specifiedQualification) {
            case MANAGER -> {personQualificationOutputDto =
                manager_Case(personQualificationInputDto, person, specifiedQualification); break;}
            case FULL_TIME_EMPLOYEE -> { personQualificationOutputDto =
                fullTimeEmployee_Case(personQualificationInputDto, person, specifiedQualification); break;}
            case PART_TIME_EMPLOYEE -> { personQualificationOutputDto =
                partTimeEmployee_Case(personQualificationInputDto, person, specifiedQualification); break;}
            case ACCOUNTANT -> { personQualificationOutputDto =
                accountant_Case(personQualificationInputDto, person, specifiedQualification); break;}
            case CLIENT -> { personQualificationOutputDto =
                client_Case(personQualificationInputDto, person, specifiedQualification); break;}
            case PROVIDER -> { personQualificationOutputDto =
                provider_Case(personQualificationInputDto, person, specifiedQualification); break;}
            case RESPONSIBLE_FOR_LEGAL_PERSON -> {
                personQualificationOutputDto =
                responsibleForLegalPerson_Case(personQualificationInputDto, person, specifiedQualification); break;}
        }

        URI uri = new PersonQualification_CreateUri().uriCreator(	uriComponentsBuilder,
													                specifiedQualification,
													                person.getId());

        var dtoRecord_ServicePersonQualification =
                new DtoRecord_ServicePersonQualification<>(uri, personQualificationOutputDto);

//        return ResponseEntity
//                .created(dtoRecord_ServicePersonQualification.uri())
//                .body(dtoRecord_ServicePersonQualification.dtoOfPerson());

        return dtoRecord_ServicePersonQualification;
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    manager_Case(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
      
//    	check behavior in test	//
    	var oldPersonQualification = managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);
/////////////////////////////////////////////////        

        var newPersonQualification = new ManagerPersonQualification(oldPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_FullTimeEmployeeAndManager(
                        person,
                        (InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto,
                        specifiedQualification);
    }


    @SuppressWarnings("unchecked")
	private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    fullTimeEmployee_Case(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);
        
        var newPersonQualification = new FullTimeEmployeePersonQualification(oldPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_FullTimeEmployeeAndManager(
                person,
                (InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto,
                specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    accountant_Case(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = accountantRepository.findAccountantPersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new AccountantPersonQualification(oldPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_Accountant(
                        person,
                        (InputDtoClass_Accountant) personQualificationInputDto,
                        specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    partTimeEmployee_Case(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new PartTimeEmployeePersonQualification(oldPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_PartTimeEmployee(
                person,
                (InputDtoClass_PartTimeEmployee) personQualificationInputDto,
                specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    client_Case(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = clientRepository.findClientPersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new ClientPersonQualification(oldPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_Client(
                person,
                (InputDtoClass_Client) personQualificationInputDto,
                specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    provider_Case(T personQualificationInputDto,
                PersonEntity person,
                String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = providerRepository.findProviderPersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new ProviderPersonQualification(oldPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_Provider(
                person,
                (InputDtoClass_Provider) personQualificationInputDto,
                specifiedQualification);
    }

	@SuppressWarnings("unchecked")
	private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
	PersonQualificationOutputDtoInterface
	responsibleForLegalPerson_Case(T personQualificationInputDto,
	              PersonEntity person,
	              String specifiedQualification ) throws ResponseStatusException {
	    var oldPersonQualification = responsibleForLegalPersonRepository.findResponsibleForLegalPersonQualificationByIsActualIsTrueAndPerson(person);
	    
	    exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);
	
	    var newPersonQualification = new ResponsibleForLegalPersonQualification(oldPersonQualification);
	    mapper.map(personQualificationInputDto, newPersonQualification);
	    
	    entitiesEqualization(oldPersonQualification, newPersonQualification);
	    entitiesSave(oldPersonQualification, newPersonQualification);
	
	    return (U) new OutputDtoClass_ResponsibleForLegalPerson(
	            person,
	            (InputDtoClass_ResponsibleForLegalPerson) personQualificationInputDto,
	            specifiedQualification);
	}

	protected void exception_For_Non_Existent_OldPersonQualification(PersonQualificationSuperclassEntity oldPersonQualification) {
		boolean existsPersonQualification = (Optional.ofNullable(oldPersonQualification)).isPresent();
		new PersonQualificationExceptions(personRepository, personQualificationRepository).exceptionForUnqualifiedPerson(existsPersonQualification);
	}

	protected void entitiesEqualization(PersonQualificationSuperclassEntity oldPersonQualification, PersonQualificationSuperclassEntity newPersonQualification) {
        oldPersonQualification.setIsActual(false);
        newPersonQualification.setIsActual(true);
        newPersonQualification.setInitialDate(LocalDateTime.now());
        newPersonQualification.setHttpVerb(HttpVerbEnum.PUT);
        newPersonQualification.setLoginUser(authenticationFacade.getAuthenticatedUsername());
    }

	protected void entitiesSave(PersonQualificationSuperclassEntity oldPersonQualification, PersonQualificationSuperclassEntity newPersonQualification) {
        personQualificationRepository.save(oldPersonQualification);
        personQualificationRepository.save(newPersonQualification);
    }

}

