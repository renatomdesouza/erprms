package br.com.erprms.serviceApplication.personService.personQualificationService;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class ManagerService {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private ManagerRepository managerRepository;
	
	public ManagerService(ModelMapper mapper, PersonRepository personRepository, ManagerRepository managerRepository) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri registerService(
				String qualification,
				DtoRecord_FullTimeEmployeeRegistry fullTimeEmployeeRecord,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		if (!personRepository.existsById(fullTimeEmployeeRecord.person_Id()))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		if ((qualification != MANAGER) || (qualification != FULL_TIME_EMPLOYEE))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"Not a full-time employee or manager");
		
		var fullTimeEmployeeDto = new DtoClass_FullTimeEmployeeRegistry(fullTimeEmployeeRecord);

		var person = personRepository.getReferenceById(fullTimeEmployeeDto.getPerson_Id());
		
		ManagerEmployeePersonQualification managerEmployee = null;
		if (qualification == MANAGER) {
			managerEmployee = mapper.map(fullTimeEmployeeDto, ManagerEmployeePersonQualification.class);

			if (managerRepository.existsEmployeePersonQualificationByFinalDateIsNullAndPerson(person))
				throw new ResponseStatusException(
						HttpStatus.INSUFFICIENT_STORAGE, 
						"An active \"Manager\" registry already exists for this Person");

			managerEmployee.setPerson(person);
			managerEmployee.setInitialDate(LocalDate.now());
			managerRepository.save(managerEmployee);
		}
		
		
		FullTimeEmployeePersonQualification fullTimeEmpĺoyee = null;
		if (qualification == FULL_TIME_EMPLOYEE) {
			fullTimeEmpĺoyee = mapper.map(fullTimeEmployeeDto, FullTimeEmployeePersonQualification.class);
		}
		
		var uri = uriComponentsBuilder
					.path("/manager/{id}")
					.buildAndExpand(managerEmployee.getId())
					.toUri();
		
		return new DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri(
					new DtoClass_FullTimeEmployeeRegistryOutput(person, managerEmployee),
					uri);
	}
	
	@Transactional   
	public Page<DtoClass_FullTimeEmployeeListing> listingService(
			String qualification,
			Pageable qualificationPageable) {  
		return managerRepository
				.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(DtoClass_FullTimeEmployeeListing::new);
	}
}
