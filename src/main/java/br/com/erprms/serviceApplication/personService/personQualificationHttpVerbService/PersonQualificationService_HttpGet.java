package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification_Page;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.OutputDtoClassPage_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.OutputDtoClassPage_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClassPage_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.OutputDtoClassPage_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.OutputDtoClassPage_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.OutputDtoClassPage_ResponsibleForLegalPerson;
import br.com.erprms.repositoryAdapter.personRepository.AccountantRepository;
import br.com.erprms.repositoryAdapter.personRepository.ClientRepository;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PartTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.ProviderRepository;
import br.com.erprms.repositoryAdapter.personRepository.ResponsibleForLegalPersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService_HttpGet {
	private final ModelMapper mapper;
	private final ManagerRepository managerRepository;
	private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
	private final PartTimeEmployeeRepository partTimeEmployeeRepository;
	private final AccountantRepository accountantRepository;
	private final ClientRepository clientRepository;
	private final ProviderRepository providerRepository;
	private final ResponsibleForLegalPersonRepository responsibleForLegalPersonRepository;
	private final PersonQualification_CreateUri createUri;
	
	public PersonQualificationService_HttpGet(
			ModelMapper mapper, 
			ManagerRepository managerRepository,
			FullTimeEmployeeRepository fullTimeEmployeeRepository,
			PartTimeEmployeeRepository partTimeEmployeeRepository,
			AccountantRepository accountantRepository,
			ClientRepository clientRepository,
			ProviderRepository providerRepository,
			ResponsibleForLegalPersonRepository responsibleForLegalPersonRepository,
			PersonQualification_CreateUri createUri) {
		this.mapper = mapper;
		this.managerRepository = managerRepository;
		this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
		this.partTimeEmployeeRepository = partTimeEmployeeRepository;
		this.accountantRepository = accountantRepository;
		this.clientRepository = clientRepository;
		this.providerRepository = providerRepository;
		this.responsibleForLegalPersonRepository = responsibleForLegalPersonRepository;
		this.createUri = createUri;
	}
	
	@Transactional   
	public 
	DtoRecord_ServicePersonQualification_Page<?> /*ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>>*/ 
	listingService(
				Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) {
		
		Page<? extends PersonQualificationOutputDtoInterface> outputDtoClassPage = null; 
		switch (specifiedQualification) {
			case MANAGER -> {
				outputDtoClassPage = managerRepository
						.findManagerPersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_FullTimeEmployeeAndManager.class)); break; }
			case FULL_TIME_EMPLOYEE -> {
				outputDtoClassPage = fullTimeEmployeeRepository
						.findEmployeePersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_FullTimeEmployeeAndManager.class)); break; }
			case PART_TIME_EMPLOYEE -> {
				outputDtoClassPage = partTimeEmployeeRepository
						.findPartTimeEmployeePersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_PartTimeEmployee.class)); break; }
			case ACCOUNTANT -> {
				outputDtoClassPage = accountantRepository
						.findAccountantPersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_Accountant.class)); break; }
			case CLIENT -> {
				outputDtoClassPage = clientRepository
						.findClientPersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_Client.class)); break; }
			case PROVIDER -> {
				outputDtoClassPage = providerRepository
						.findProviderPersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_Provider.class)); break; }
			case RESPONSIBLE_FOR_LEGAL_PERSON -> {
				outputDtoClassPage = responsibleForLegalPersonRepository
						.findResponsibleForLegalPersonQualificationByIsActualIsTrue(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_ResponsibleForLegalPerson.class)); break; }
		};

		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification);

		var responseEntityOutputDtoPage = new DtoRecord_ServicePersonQualification_Page<>(uri, outputDtoClassPage);
		
//		return ResponseEntity
//				.created(responseEntityOutputDtoPage.uri())
//				.body(responseEntityOutputDtoPage.page());

		return responseEntityOutputDtoPage;
	
	
	}
}
