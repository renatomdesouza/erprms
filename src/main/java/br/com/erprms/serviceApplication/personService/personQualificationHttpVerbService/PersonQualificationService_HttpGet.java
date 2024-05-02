package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import java.net.URI;

import br.com.erprms.dtoPort.personDto.personQualificationDto.DtoRecord_ServicePersonQualification_Page;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.OutputDtoClassPage_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.OutputDtoClassPage_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.OutputDtoClassPage_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.OutputDtoClassPage_ResponsibleForLegalPerson;
import br.com.erprms.repositoryAdapter.personRepository.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.OutputDtoClassPage_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.OutputDtoClassPage_PartTimeEmployee;
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
	public ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>> listingService(
				Pageable qualificationPageable,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) {
		
		Page<? extends PersonQualificationOutputDtoInterface> outputDtoClassPage = null; 
		switch (specifiedQualification) {
			case MANAGER -> {
				outputDtoClassPage = managerRepository
						.findManagerPersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_FullTimeEmployeeAndManager.class)); break; }
			case FULL_TIME_EMPLOYEE -> {
				outputDtoClassPage = fullTimeEmployeeRepository
						.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_FullTimeEmployeeAndManager.class)); break; }
			case PART_TIME_EMPLOYEE -> {
				outputDtoClassPage = partTimeEmployeeRepository
						.findPartTimeEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_PartTimeEmployee.class)); break; }
			case ACCOUNTANT -> {
				outputDtoClassPage = accountantRepository
						.findAccountantPersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_Accountant.class)); break; }
			case CLIENT -> {
				outputDtoClassPage = clientRepository
						.findClientPersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_Client.class)); break; }
			case PROVIDER -> {
				outputDtoClassPage = providerRepository
						.findProviderPersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_Provider.class)); break; }
			case RESPONSIBLE_FOR_LEGAL_PERSON -> {
				outputDtoClassPage = responsibleForLegalPersonRepository
						.findResponsibleForLegalPersonQualificationByFinalDateIsNull(qualificationPageable)
						.map(p -> mapper.map(p, OutputDtoClassPage_ResponsibleForLegalPerson.class)); break; }
		};

		URI uri = createUri.uriCreator(	uriComponentsBuilder, 
										specifiedQualification);

		var responseEntityOutputDtoPage = new DtoRecord_ServicePersonQualification_Page<>(uri, outputDtoClassPage);
		
		return ResponseEntity
				.created(responseEntityOutputDtoPage.uri())
				.body(responseEntityOutputDtoPage.page());
	}
}
