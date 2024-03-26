package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.EmployeeInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;


@Service
public class ManagerService {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private ManagerRepository managerRepository;
	private PersonQualificationRepository personQualificationRepository;
	private GeneralExclude_PersonQualificationService genereralExclude;
	private final PersonQualification_ValidatorService validatorService;
	
	public ManagerService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			ManagerRepository managerRepository,
			PersonQualificationRepository personQualificationRepository,
			GeneralExclude_PersonQualificationService genereralExclude,
			PersonQualification_ValidatorService validatorService) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
		this.personQualificationRepository = personQualificationRepository;
		this.genereralExclude = genereralExclude;
		this.validatorService = validatorService;
	}

	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri registerService(
				DtoRecord_FullTimeEmployeeRegistry fullTimeManagerRecordDto,
				UriComponentsBuilder uriComponentsBuilder,
				String specifiedQualification) 
				throws ResponseStatusException {
		
		validatorService.registerServiceValidation(fullTimeManagerRecordDto.person_Id(), specifiedQualification);
		
		var fullTimeManagerClassDto = new DtoClass_ManagerAndFullTimeEmployeeRegistry(fullTimeManagerRecordDto);

		var person = personRepository.getReferenceById(fullTimeManagerClassDto.getPerson_Id());
		
		var managerEntity = mapper.map(fullTimeManagerClassDto, ManagerPersonQualification.class);

		managerEntity.setPerson(person);
		managerEntity.setInitialDate(LocalDate.now());
		personQualificationRepository.save(managerEntity);
		
		var uri = uriComponentsBuilder
					.path("/" + specifiedQualification + "/{id}")
					.buildAndExpand(person.getId())
					.toUri();
		
		return new DtoRecord_ManagerAndFullTimeEmployeeOutputRegistry_With_Uri(
				new DtoClass_ManagerAndFullTimeEmployeeRegistryOutput(
						person, 
						fullTimeManagerClassDto, 
						specifiedQualification),
				uri);
//		return new DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri(
//					new DtoClass_ManagerEmployeeRegistryOutput(personEntity, managerEntity),
//					uri);
//		return null;
	}
	
	@Transactional   
	public Page<DtoClass_ManagerAndFullTimeEmployeeToListing> listingService(Pageable qualificationPageable) {  
		return managerRepository
				.findManagerPersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(p -> mapper.map(p, DtoClass_ManagerAndFullTimeEmployeeToListing.class));
//				.map(DtoClass_ManagerEmployeeListing::new);
	}

	@Transactional
	public void exclude(Long person_Id, String specifiedQualification) {
		genereralExclude.generalExclude(person_Id, specifiedQualification);
	}
	
}
