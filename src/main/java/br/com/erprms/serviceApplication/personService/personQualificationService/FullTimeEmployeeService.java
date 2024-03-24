package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.repositoryAdapter.personRepository.FullTimeEmployeeRepository;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.PersonQualifService_HttpGet;
import jakarta.transaction.Transactional;

@Service
public class FullTimeEmployeeService {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private final PersonQualificationRepository personQualificationRepository;
	private final PersonQualifService_HttpGet personQualifGet;
	private final FullTimeEmployeeRepository fullTimeEmployeeRepository;
	private final ManagerRepository managerRepository;
	
	
	public FullTimeEmployeeService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			PersonQualificationRepository personQualificationRepository,
			PersonQualifService_HttpGet personQualifGet,
			FullTimeEmployeeRepository fullTimeEmployeeRepository,
			ManagerRepository managerRepository) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.personQualifGet = personQualifGet;
		this.fullTimeEmployeeRepository = fullTimeEmployeeRepository;
		this.managerRepository = managerRepository;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri registerService(
				DtoRecord_FullTimeEmployeeRegistry fullTimeEmployeeRecord,   // DtoRecord_FullTimeEmployeeRegistry
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) 
				throws ResponseStatusException {

		registerServiceValidation(fullTimeEmployeeRecord.person_Id(), specifiedQualification);
		
		DtoClass_FullTimeEmployeeRegistry fullTimeEmployeeDto = 
				new DtoClass_FullTimeEmployeeRegistry( (DtoRecord_FullTimeEmployeeRegistry) fullTimeEmployeeRecord);

		var fullTimeEmployee = mapper.map(fullTimeEmployeeDto, FullTimeEmployeePersonQualification.class);

		var person = personRepository.getReferenceById(fullTimeEmployeeDto.getPerson_Id() );
		
		fullTimeEmployee.setPerson(person);
		
		fullTimeEmployee.setInitialDate(LocalDate.now());
		
		personQualificationRepository.save(fullTimeEmployee);
		
		var uri = uriComponentsBuilder
					.path("/manager/{id}")
					.buildAndExpand(person.getId())
					.toUri();
		
		return new DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri(
					new DtoClass_FullTimeEmployeeRegistryOutput(person, fullTimeEmployee),
					uri);
	}
	
	@Transactional   
	public Page<DtoClass_FullTimeEmployeeToListing> listingService(Pageable qualificationPageable) {
		return fullTimeEmployeeRepository.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(p -> mapper.map(p, DtoClass_FullTimeEmployeeToListing.class));  
	}


	@Transactional   
	public Page<DtoClass_FullTimeEmployeeToListing> listingService2(Pageable qualificationPageable) {  //FullTimeEmployeePersonQualification
		var pagesEmployee = fullTimeEmployeeRepository.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable);
		Type naturalPersonListingPageType = new TypeToken<Page<DtoClass_FullTimeEmployeeToListing>>(){}.getType();
		Page<DtoClass_FullTimeEmployeeToListing> pagesDto = mapper.map(pagesEmployee, naturalPersonListingPageType);
		return pagesDto;
//		return fullTimeEmployeeRepository.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
//				.map(p -> mapper.map(p, DtoClass_FullTimeEmployeeToListing.class));  
	}

	

	private void registerServiceValidation(@NonNull Long id_Person, String specifiedQualification) {
		
		if (!personRepository.existsById(id_Person))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		Optional<PersonQualificationSuperclassEntity> fullTimeEmployeeOptional = Optional.ofNullable(
				personQualificationRepository.personActiveQualification(id_Person, specifiedQualification));
		if (!fullTimeEmployeeOptional.isEmpty())
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no an active \"FullTimeEmployee\" registry for this Person");
	}	
}
