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
import org.springframework.http.ResponseEntity;
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
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.OutputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.InputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.OutputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.OutputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.OutputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.InputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.OutputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.InputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.OutputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.infrastructure.exceptionManager.responseStatusException.PersonQualificationExceptions;
import br.com.erprms.infrastructure.getAuthentication.AuthenticationFacade;
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
    private final AuthenticationFacade authenticationFacade;

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
            AuthenticationFacade authenticationFacade,
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
    @SuppressWarnings("null")
    public <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface> ResponseEntity<PersonQualificationOutputDtoInterface> update(
            T personQualificationInputDto,
            UriComponentsBuilder uriComponentsBuilder,
            String specifiedQualification)
            throws ResponseStatusException {

        var person = personRepository.getReferenceById(personQualificationInputDto.getPerson_Id());

        exceptionService.exceptionForPersonWhoDoesNotExist(personQualificationInputDto.getPerson_Id());

        PersonQualificationOutputDtoInterface personQualificationOutputDto = null;
        switch (specifiedQualification) {
            case MANAGER -> {personQualificationOutputDto =
                case_MANAGER(personQualificationInputDto, person, specifiedQualification); break;}
            case FULL_TIME_EMPLOYEE -> { personQualificationOutputDto =
                case_FULL_TIME_EMPLOYEE(personQualificationInputDto, person, specifiedQualification); break;}
            case PART_TIME_EMPLOYEE -> { personQualificationOutputDto =
                case_PART_TIME_EMPLOYEE(personQualificationInputDto, person, specifiedQualification); break;}
            case ACCOUNTANT -> { personQualificationOutputDto =
                case_ACCOUNTANT(personQualificationInputDto, person, specifiedQualification); break;}
            case CLIENT -> { personQualificationOutputDto =
                case_CLIENT(personQualificationInputDto, person, specifiedQualification); break;}
            case PROVIDER -> { personQualificationOutputDto =
                case_PROVIDER(personQualificationInputDto, person, specifiedQualification); break;}
            case RESPONSIBLE_FOR_LEGAL_PERSON -> {
                System.out.println(" =======> ****************************************** " );
                personQualificationOutputDto =
                case_RESPONSIBLE_FOR_LEGAL_PERSON(personQualificationInputDto, person, specifiedQualification); break;}
        }

        URI uri = createUri.uriCreator(	uriComponentsBuilder,
                specifiedQualification,
                person.getId());

        var dtoRecord_ServicePersonQualification =
                new DtoRecord_ServicePersonQualification<>(uri, personQualificationOutputDto);

        return ResponseEntity
                .created(dtoRecord_ServicePersonQualification.uri())
                .body(dtoRecord_ServicePersonQualification.dtoOfPerson());

    }

    @SuppressWarnings("unchecked")
    private <T extends PersonQualificationInputDtoInterface, U  extends PersonQualificationOutputDtoInterface>
    PersonQualificationOutputDtoInterface
    case_MANAGER(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = managerRepository.findManagerPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

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
    case_FULL_TIME_EMPLOYEE(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = fullTimeEmployeeRepository.findFullTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

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
    case_ACCOUNTANT(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = accountantRepository.findAccountantPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

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
    case_PART_TIME_EMPLOYEE(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = partTimeEmployeeRepository.findPartTimeEmployeePersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

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
    case_CLIENT(T personQualificationInputDto,
                            PersonEntity person,
                            String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = clientRepository.findClientPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

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
    case_PROVIDER(T personQualificationInputDto,
                PersonEntity person,
                String specifiedQualification ) throws ResponseStatusException {
        var oldPersonQualification = providerRepository.findProviderPersonQualificationByIsActualIsTrueAndPerson(person);
        exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

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
case_RESPONSIBLE_FOR_LEGAL_PERSON(T personQualificationInputDto,
              PersonEntity person,
              String specifiedQualification ) throws ResponseStatusException {

    var oldPersonQualification = responsibleForLegalPersonRepository.findResponsibleForLegalPersonQualificationByIsActualIsTrueAndPerson(person);
    exceptionService.exceptionForUnqualifiedPerson(Optional.ofNullable(oldPersonQualification));

    var newPersonQualification = new ResponsibleForLegalPersonQualification(oldPersonQualification);
    mapper.map(personQualificationInputDto, newPersonQualification);
    entitiesEqualization(oldPersonQualification, newPersonQualification);
    entitiesSave(oldPersonQualification, newPersonQualification);

    return (U) new OutputDtoClass_ResponsibleForLegalPerson(
            person,
            (InputDtoClass_ResponsibleForLegalPerson) personQualificationInputDto,
            specifiedQualification);
}

    private void entitiesEqualization(PersonQualificationSuperclassEntity oldPersonQualification, PersonQualificationSuperclassEntity newPersonQualification) {
        oldPersonQualification.setIsActual(false);
        newPersonQualification.setIsActual(true);
        newPersonQualification.setInitialDate(LocalDateTime.now());
        newPersonQualification.setHttpVerb(HttpVerbEnum.PUT);
        newPersonQualification.setLoginUser(authenticationFacade.getAuthentication());
    }

    private void entitiesSave(PersonQualificationSuperclassEntity oldPersonQualification, PersonQualificationSuperclassEntity newPersonQualification) {
        personQualificationRepository.save(oldPersonQualification);
        personQualificationRepository.save(newPersonQualification);
    }

}
