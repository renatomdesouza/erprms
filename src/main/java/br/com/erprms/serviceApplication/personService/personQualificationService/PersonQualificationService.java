package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualificationService {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private ManagerRepository managerRepository;
	
	public PersonQualificationService(ModelMapper mapper, PersonRepository personRepository, ManagerRepository managerRepository) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri managerServiceRegistry(
				String qualification,
				DtoRecord_FullTimeEmployeeRegistry managerRecord,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		if (!personRepository.existsById(managerRecord.person_Id()))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		var managerClassDto = new DtoClass_FullTimeEmployeeRegistry(managerRecord);
		var managerEmployee = mapper.map(managerClassDto, ManagerPersonQualification.class);
		var person = personRepository.getReferenceById(managerClassDto.getPerson_Id());
		
		managerEmployee.setPerson(person);
		if (managerRepository.existsManagerEmployeePersonQualificationByFinalDateIsNullAndPerson(person))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"An active \"Manager\" registry already exists for this Person");
		
		managerEmployee.setInitialDate(LocalDate.now());
		managerRepository.save(managerEmployee);
		
		var uri = uriComponentsBuilder
					.path("/manager/{id}")
					.buildAndExpand(managerEmployee.getId())
					.toUri();
		
//		return new DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri(
//					new DtoClass_ManagerEmployeeRegistryOutput(person, managerEmployee),
//					uri);
		return null;
	}
	
	@Transactional   
	public Page<DtoClass_FullTimeEmployeeListing> managerServiceListing(
			String qualification,
			Pageable qualificationPageable) {  
		return managerRepository
				.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(DtoClass_FullTimeEmployeeListing::new);
	}
}

