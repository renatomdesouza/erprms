package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

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
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.OutputExcludeDto_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.OutputExcludeDto_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.OutPutExcludeDto_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.OutputDtoClassExclude_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.OutputExcludeDto_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.OutputExcludeDto_ResponsibleForLegalPerson;
import br.com.erprms.infrastructure.exceptionManager.ResponseStatus_Exception;
import br.com.erprms.infrastructure.springSecurity.AuthenticationFacade;
import br.com.erprms.repositoryAdapter.personRepository.*;
import br.com.erprms.serviceApplication.personService.StatusPerson;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.*;

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
    private final ResponseStatus_Exception exceptionService;
    private final PersonQualification_CreateUri createUri;
    private final StatusPerson statusPersonOfQualification;
    private final AuthenticationFacade authenticationFacade;

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
            ResponseStatus_Exception exceptionService,
            PersonQualification_CreateUri createUri,
            StatusPerson statusPersonOfQualification,
            AuthenticationFacade authenticationFacade) {
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
        this.exceptionService = exceptionService;
        this.createUri = createUri;
        this.statusPersonOfQualification = statusPersonOfQualification;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional
    public ResponseEntity<PersonQualificationOutputDtoInterface> exclude(
            @NonNull Long person_Id,
            UriComponentsBuilder uriComponentsBuilder,
            String specifiedQualification)
            throws ResponseStatusException {

        exceptionService.exceptionForPersonWhoDoesNotExist(person_Id);
        var person = personRepository.getReferenceById(person_Id);
        statusPersonOfQualification.setSatusOfNonUse(person);

        PersonQualificationOutputDtoInterface outPutExcludeDto = null;
        switch (specifiedQualification) {
            case FULL_TIME_EMPLOYEE -> { outPutExcludeDto = case_FULL_TIME_EMPLOYEE(specifiedQualification, person); break; }
            case MANAGER -> { outPutExcludeDto = case_MANAGER(specifiedQualification, person); break; }
            case PART_TIME_EMPLOYEE -> { outPutExcludeDto = case_PART_TIME_EMPLOYEE(specifiedQualification, person); break; }
            case ACCOUNTANT -> { outPutExcludeDto = case_ACCOUNTANT(specifiedQualification, person); break; }
            case CLIENT -> { outPutExcludeDto = case_CLIENT(specifiedQualification, person); break; }
            case PROVIDER -> { outPutExcludeDto = case_PROVIDER(specifiedQualification, person); break; }
            case RESPONSIBLE_FOR_LEGAL_PERSON -> { outPutExcludeDto = case_RESPONSIBLE_FOR_LEGAL_PERSON(specifiedQualification, person); break; }
        }

        var uri = createUri.uriCreator(	uriComponentsBuilder,
                specifiedQualification,
                person_Id);

        var dtoRecord_ServicePersonQualification =
                new DtoRecord_ServicePersonQualification<>(uri, outPutExcludeDto);

        return ResponseEntity
                .created(dtoRecord_ServicePersonQualification.uri())
                .body(dtoRecord_ServicePersonQualification.dtoOfPerson());
    }

    private PersonQualificationOutputDtoInterface case_MANAGER(String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new ManagerPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutPutExcludeDto_FullTimeEmployeeAndManager(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface case_FULL_TIME_EMPLOYEE (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new FullTimeEmployeePersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutPutExcludeDto_FullTimeEmployeeAndManager(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface case_PART_TIME_EMPLOYEE (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new PartTimeEmployeePersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutputDtoClassExclude_PartTimeEmployee(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface case_ACCOUNTANT (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = accountantRepository.findAccountantPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new AccountantPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_Accountant(	newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface case_CLIENT (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = clientRepository.findClientPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new ClientPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_Client(	newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface case_PROVIDER (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = providerRepository.findProviderPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new ProviderPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_Provider(newPersonQualification, specifiedQualification);
    }

    private PersonQualificationOutputDtoInterface case_RESPONSIBLE_FOR_LEGAL_PERSON (String specifiedQualification, PersonEntity person) {
        var oldPersonQualification = responsibleForLegalPersonRepository.findResponsibleForLegalPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

        var newPersonQualification = new ResponsibleForLegalPersonQualification(oldPersonQualification);
        mapper.map(oldPersonQualification, newPersonQualification);
        entitiesEqualization(oldPersonQualification, newPersonQualification);
        entitiesSave(oldPersonQualification, newPersonQualification);

        return new OutputExcludeDto_ResponsibleForLegalPerson(newPersonQualification, specifiedQualification);
    }

    private void entitiesEqualization(PersonQualificationSuperclassEntity oldPersonQualification, PersonQualificationSuperclassEntity newPersonQualification) {
        oldPersonQualification.setIsActual(false);
        newPersonQualification.setIsActual(true);
        newPersonQualification.setFinalDate(LocalDateTime.now());
        newPersonQualification.setHttpVerb(HttpVerbEnum.DELETE);
        newPersonQualification.setLoginUser(authenticationFacade.getAuthentication());
    }

    private void entitiesSave(PersonQualificationSuperclassEntity oldPersonQualification, PersonQualificationSuperclassEntity newPersonQualification) {
        personQualificationRepository.save(oldPersonQualification);
        personQualificationRepository.save(newPersonQualification);
    }
}
