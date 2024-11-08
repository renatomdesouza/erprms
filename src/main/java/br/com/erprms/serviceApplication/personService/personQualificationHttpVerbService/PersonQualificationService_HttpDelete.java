package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
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
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.OutputExcludeDto_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.OutputExcludeDto_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutPutExcludeDto_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.OutputDtoClassExclude_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.OutputExcludeDto_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.OutputExcludeDto_ResponsibleForLegalPerson;
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
import br.com.erprms.serviceApplication.personService.StatusPerson_Setter;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpDelete {
    private final ModelMapper mapper;
    private final PersonRepository personRepository;
    private final ManagerRepository managerRepository;
    private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
    private final PartTimeEmployeeRepository partTimeEmployeeRepository;
    private final AccountantRepository accountantRepository;
    private final ClientRepository clientRepository;
    private final ProviderRepository providerRepository;
    private final ResponsibleForLegalPersonRepository responsibleForLegalPersonRepository;
    private final PersonQualificationRepository personQualificationRepository;
    private final AuthenticatedUsername authenticationFacade;

    public PersonQualificationService_HttpDelete(
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
    public  DtoRecord_ServicePersonQualification<PersonQualificationOutputDtoInterface> exclude(
            @NonNull Long person_Id,
            UriComponentsBuilder uriComponentsBuilder,
            String specifiedQualification) throws ResponseStatusException {
    	PersonEntity person = null;
    	person = personRepository.getReferenceById(person_Id);
    	
    	boolean notExistsPerson = (Optional.ofNullable(person)).isEmpty();
        new PersonQualificationExceptions(personQualificationRepository).exceptionForPersonWhoDoesNotExist(notExistsPerson);
        
        PersonQualificationOutputDtoInterface outPutExcludeDto = null;
        switch (specifiedQualification) {
            case FULL_TIME_EMPLOYEE -> { outPutExcludeDto = fullTimeEmployee_Case(specifiedQualification, person); break; }
            case MANAGER -> { outPutExcludeDto = manager_Case(specifiedQualification, person); break; }
            case PART_TIME_EMPLOYEE -> { outPutExcludeDto = partTimeEmployee_Case(specifiedQualification, person); break; }
            case ACCOUNTANT -> { outPutExcludeDto = accountant_Case(specifiedQualification, person); break; }
            case CLIENT -> { outPutExcludeDto = client_Case(specifiedQualification, person); break; }
            case PROVIDER -> { outPutExcludeDto = provider_Case(specifiedQualification, person); break; }
            case RESPONSIBLE_FOR_LEGAL_PERSON -> { outPutExcludeDto = responsibleForLegalPerson_Case(specifiedQualification, person); break; }
        }
        
        new StatusPerson_Setter(personRepository, personQualificationRepository).setSatusOfNonUse(person);

        var uri = new PersonQualification_CreateUri().uriCreator(	uriComponentsBuilder,
													                specifiedQualification,
													                person_Id);

        var dtoRecord_ServicePersonQualification =
                new DtoRecord_ServicePersonQualification<>(uri, outPutExcludeDto);

        return dtoRecord_ServicePersonQualification;
    }
    
    protected List<PersonQualificationSuperclassEntity> personsQualifications_ConfigureAndSave(
    		PersonQualificationSuperclassEntity oldPersonQualification, 
    		PersonQualificationSuperclassEntity newPersonQualification) {
        oldPersonQualification.setIsActual(false);
        newPersonQualification.setIsActual(true);
        newPersonQualification.setFinalDate(nowSetter());
        newPersonQualification.setHttpVerb(HttpVerbEnum.DELETE);
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

    private PersonQualificationOutputDtoInterface manager_Case(String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(person);

        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new ManagerPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutPutExcludeDto_FullTimeEmployeeAndManager(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface fullTimeEmployee_Case (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);

        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new FullTimeEmployeePersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutPutExcludeDto_FullTimeEmployeeAndManager(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface partTimeEmployee_Case (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);

        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new PartTimeEmployeePersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutputDtoClassExclude_PartTimeEmployee(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface accountant_Case (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = accountantRepository.findAccountantPersonQualificationByIsActualIsTrueAndPerson(person);

        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);
        
        var newPersonQualification = new AccountantPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_Accountant(	newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface client_Case (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = clientRepository.findClientPersonQualificationByIsActualIsTrueAndPerson(person);
        
        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);
        
        var newPersonQualification = new ClientPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_Client(	newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface provider_Case (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = providerRepository.findProviderPersonQualificationByIsActualIsTrueAndPerson(person);

        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new ProviderPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_Provider(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface responsibleForLegalPerson_Case (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = responsibleForLegalPersonRepository.findResponsibleForLegalPersonQualificationByIsActualIsTrueAndPerson(person);

        exception_For_Non_Existent_OldPersonQualification(oldPersonQualification);

        var newPersonQualification = new ResponsibleForLegalPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        personsQualifications_ConfigureAndSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_ResponsibleForLegalPerson(newPersonQualification, specifiedQualification);
    }
    
    private void exception_For_Non_Existent_OldPersonQualification(PersonQualificationSuperclassEntity oldPersonQualification) {		
		boolean notExistsPersonQualification = (Optional.ofNullable(oldPersonQualification)).isEmpty();
		new PersonQualificationExceptions(personQualificationRepository).exceptionForQualifiedPersonWhoDoesNotExist(notExistsPersonQualification);
	}
}
