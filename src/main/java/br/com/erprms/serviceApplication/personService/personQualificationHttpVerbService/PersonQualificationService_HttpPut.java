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
import java.util.ArrayList;
import java.util.List;
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
            AuthenticatedUsername authenticationFacade) {
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
    }

    @Transactional
    public <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface> 
    DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> 
    update(	T personQualificationInputDto,
            UriComponentsBuilder uriComponentsBuilder,
            String specifiedQualification) throws ResponseStatusException {
    	PersonEntity person = null;
        person = personRepository.getReferenceById(personQualificationInputDto.getPerson_Id());

        boolean notExistsPerson =  (Optional.ofNullable(person)).isEmpty(); 
        new PersonQualificationExceptions(personQualificationRepository).exceptionForPersonWhoDoesNotExist(notExistsPerson);
        
        PersonQualificationSuperclassEntity oldPersonQualification = findOldPersonQuailfication(person, specifiedQualification);
        
        boolean notExistsPersonQualification = (Optional.ofNullable(oldPersonQualification)).isEmpty();
		new PersonQualificationExceptions(personQualificationRepository).exceptionForQualifiedPersonWhoDoesNotExist(notExistsPersonQualification);
        
        PersonQualificationOutputDtoInterface personQualificationOutputDto = updateSelected(	personQualificationInputDto,
	        																					oldPersonQualification,
																								specifiedQualification, 
																								person);

        URI uri = new PersonQualification_CreateUri().uriCreator(	uriComponentsBuilder,
													                specifiedQualification,
													                person.getId());

        var dtoRecord_ServicePersonQualification =
                new DtoRecord_ServicePersonQualification<>(uri, personQualificationOutputDto);

        return dtoRecord_ServicePersonQualification;
    }
    
    
	protected PersonQualificationSuperclassEntity findOldPersonQuailfication(PersonEntity person, String specifiedQualification) {
		PersonQualificationSuperclassEntity oldPersonQualification = null;

		switch (specifiedQualification) {
	        case MANAGER -> { oldPersonQualification = 
        		managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(person);break;}
	        case FULL_TIME_EMPLOYEE -> { oldPersonQualification =
    			fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person); break;}
	        case PART_TIME_EMPLOYEE -> { oldPersonQualification =
        		partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person); break;}
	        case ACCOUNTANT -> { oldPersonQualification =
        		accountantRepository.findAccountantPersonQualificationByIsActualIsTrueAndPerson(person); break;}
	        case CLIENT -> { oldPersonQualification =
        		clientRepository.findClientPersonQualificationByIsActualIsTrueAndPerson(person); break;}
	        case PROVIDER -> { oldPersonQualification = 
        		providerRepository.findProviderPersonQualificationByIsActualIsTrueAndPerson(person); break;}
	        case RESPONSIBLE_FOR_LEGAL_PERSON -> { oldPersonQualification = 
	        	responsibleForLegalPersonRepository.findResponsibleForLegalPersonQualificationByIsActualIsTrueAndPerson(person); break;}}
		
        return oldPersonQualification;
	}

	protected <T extends PersonQualificationInputDtoInterface> PersonQualificationOutputDtoInterface updateSelected(
			T personQualificationInputDto, 
			PersonQualificationSuperclassEntity oldPersonQuailfication, 
			String specifiedQualification, 
			PersonEntity person) {
		PersonQualificationOutputDtoInterface personQualificationOutputDto = null;
        
		switch (specifiedQualification) {
            case MANAGER -> {personQualificationOutputDto =
                manager_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}
            case FULL_TIME_EMPLOYEE -> { personQualificationOutputDto =
                fullTimeEmployee_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}
            case PART_TIME_EMPLOYEE -> { personQualificationOutputDto =
                partTimeEmployee_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}
            case ACCOUNTANT -> { personQualificationOutputDto =
                accountant_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}
            case CLIENT -> { personQualificationOutputDto =
                client_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}
            case PROVIDER -> { personQualificationOutputDto =
                provider_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}
            case RESPONSIBLE_FOR_LEGAL_PERSON -> { personQualificationOutputDto =
                responsibleForLegalPerson_Case(personQualificationInputDto, oldPersonQuailfication, person, specifiedQualification); break;}}
		
		return personQualificationOutputDto;
	}

	protected List<PersonQualificationSuperclassEntity> personsQualifications_ConfigureAndSave(
			PersonQualificationSuperclassEntity oldPersonQualification, 
			PersonQualificationSuperclassEntity newPersonQualification) {
		oldPersonQualification.setIsActual(false);
        newPersonQualification.setIsActual(true);
        newPersonQualification.setInitialDate(nowSetter());
        newPersonQualification.setHttpVerb(HttpVerbEnum.PUT);
        newPersonQualification.setLoginUser(authenticationFacade.getAuthenticatedUsername());
       
        List<PersonQualificationSuperclassEntity> qualifications = new ArrayList<>();
        qualifications.add(oldPersonQualification);
        qualifications.add(newPersonQualification);

        personQualificationRepository.saveAll(qualifications);
        
        return qualifications;        
    }

	protected LocalDateTime nowSetter() {
		return LocalDateTime.now();
	}

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    manager_Case(	T personQualificationInputDto,
		    		PersonQualificationSuperclassEntity oldPersonQuailfication,
		            PersonEntity person,
		            String specifiedQualification ) throws ResponseStatusException{
    	
    	ManagerPersonQualification oldManagerPersonQualification = (ManagerPersonQualification) oldPersonQuailfication;
    	
        var newPersonQualification = new ManagerPersonQualification(oldManagerPersonQualification );
        mapper.map(personQualificationInputDto, newPersonQualification);
      
        
        
        personsQualifications_ConfigureAndSave(oldManagerPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_FullTimeEmployeeAndManager(
                        person,
                        (InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto,
                        specifiedQualification);
    }
    
    @SuppressWarnings("unchecked")
	private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    fullTimeEmployee_Case(	T personQualificationInputDto,
 							PersonQualificationSuperclassEntity oldPersonQuailfication,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
    	FullTimeEmployeePersonQualification oldFullTimeEmployeePersonQualification = (FullTimeEmployeePersonQualification) oldPersonQuailfication;
        
        var newPersonQualification = new FullTimeEmployeePersonQualification(oldFullTimeEmployeePersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        personsQualifications_ConfigureAndSave(oldFullTimeEmployeePersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_FullTimeEmployeeAndManager(
                person,
                (InputDtoClass_FullTimeEmployeeAndManager) personQualificationInputDto,
                specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    accountant_Case(	T personQualificationInputDto,
	    				PersonQualificationSuperclassEntity oldPersonQuailfication,
	                    PersonEntity person,
	                    String specifiedQualification ) throws ResponseStatusException {
    	AccountantPersonQualification oldAccountantPersonQualification = (AccountantPersonQualification) oldPersonQuailfication;
        
        var newPersonQualification = new AccountantPersonQualification(oldAccountantPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        personsQualifications_ConfigureAndSave(oldAccountantPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_Accountant(
                        person,
                        (InputDtoClass_Accountant) personQualificationInputDto,
                        specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    partTimeEmployee_Case(	T personQualificationInputDto,
    						PersonQualificationSuperclassEntity oldPersonQuailfication,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
    	PartTimeEmployeePersonQualification oldPartTimeEmployeePersonQualification = (PartTimeEmployeePersonQualification) oldPersonQuailfication;

        var newPersonQualification = new PartTimeEmployeePersonQualification(oldPartTimeEmployeePersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        personsQualifications_ConfigureAndSave(oldPartTimeEmployeePersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_PartTimeEmployee(
                person,
                (InputDtoClass_PartTimeEmployee) personQualificationInputDto,
                specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    client_Case(	T personQualificationInputDto,
					PersonQualificationSuperclassEntity oldPersonQuailfication,
    				PersonEntity person,
                    String specifiedQualification ) throws ResponseStatusException {
    	ClientPersonQualification oldClientPersonQualification = (ClientPersonQualification) oldPersonQuailfication;

        var newPersonQualification = new ClientPersonQualification(oldClientPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        personsQualifications_ConfigureAndSave(oldClientPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_Client(
                person,
                (InputDtoClass_Client) personQualificationInputDto,
                specifiedQualification);
    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    provider_Case(	T personQualificationInputDto,
					PersonQualificationSuperclassEntity oldPersonQuailfication,
	                PersonEntity person,
	                String specifiedQualification ) throws ResponseStatusException {
    	ProviderPersonQualification oldProviderPersonQualification = (ProviderPersonQualification) oldPersonQuailfication;

        var newPersonQualification = new ProviderPersonQualification(oldProviderPersonQualification);
        mapper.map(personQualificationInputDto, newPersonQualification);
        
        personsQualifications_ConfigureAndSave(oldProviderPersonQualification, newPersonQualification);

        return (U) new OutputDtoClass_Provider(
                person,
                (InputDtoClass_Provider) personQualificationInputDto,
                specifiedQualification);
    }

	@SuppressWarnings("unchecked")
	private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
	PersonQualificationOutputDtoInterface
	responsibleForLegalPerson_Case(	T personQualificationInputDto,
									PersonQualificationSuperclassEntity oldPersonQuailfication,
						            PersonEntity person,
						            String specifiedQualification ) throws ResponseStatusException {
		ResponsibleForLegalPersonQualification oldResponsibleForLegalPersonQualification = (ResponsibleForLegalPersonQualification) oldPersonQuailfication;
	
	    var newPersonQualification = new ResponsibleForLegalPersonQualification(oldResponsibleForLegalPersonQualification);
	    mapper.map(personQualificationInputDto, newPersonQualification);
	    
	    personsQualifications_ConfigureAndSave(oldResponsibleForLegalPersonQualification, newPersonQualification);
	
	    return (U) new OutputDtoClass_ResponsibleForLegalPerson(
	            person,
	            (InputDtoClass_ResponsibleForLegalPerson) personQualificationInputDto,
	            specifiedQualification);
	}
}

