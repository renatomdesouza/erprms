package br.com.erprms.serviceApplication.personService;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.employeePersonQualificatorInheritor.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToListingOfQualification;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToOutputManagerOfRegistry;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToRegistryOfManager;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecordToOutputManagerOfRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecordToRegistryOfManager;
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
	public DtoRecordToOutputManagerOfRegistry_With_Uri managerServiceRegistry(
				DtoRecordToRegistryOfManager managerRecord,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		if (!personRepository.existsById(managerRecord.person_Id()))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		var managerClassDto = new DtoClassToRegistryOfManager(managerRecord);
		var managerEmployee = mapper.map(managerClassDto, ManagerEmployeePersonQualificationSubclass.class);
		var person = personRepository.getReferenceById(managerClassDto.getPerson_Id());
		
		managerEmployee.setPerson(person);
		if (managerRepository.existsEmployeePersonQualificationSubclassByFinalDateIsNullAndPerson(person))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"An active \"Manager\" registry already exists for this Person");
		
		managerEmployee.setInitialDate(LocalDate.now());
		managerRepository.save(managerEmployee);
		
		var uri = uriComponentsBuilder
					.path("/manager/{id}")
					.buildAndExpand(managerEmployee.getId())
					.toUri();
		
		return new DtoRecordToOutputManagerOfRegistry_With_Uri(
					new DtoClassToOutputManagerOfRegistry(person, managerEmployee),
					uri);
	}
	
	@Transactional   
	public Page<DtoClassToListingOfQualification> managerServiceListing(Pageable qualificationPageable) {  
		return managerRepository
				.findEmployeePersonQualificationSubclassByFinalDateIsNull(qualificationPageable)
				.map(DtoClassToListingOfQualification::new);
	}
}

