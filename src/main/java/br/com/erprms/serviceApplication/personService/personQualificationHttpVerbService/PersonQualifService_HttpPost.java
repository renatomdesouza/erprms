package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClass_OutputManagerOfRegistry;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClass_RegistryOfManager;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecord_OutputManagerOfRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecord_RegistryOfManager;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualifService_HttpPost {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private ManagerRepository managerRepository;
	
	public PersonQualifService_HttpPost(ModelMapper mapper, PersonRepository personRepository, ManagerRepository managerRepository) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
	}
	
	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_OutputManagerOfRegistry_With_Uri registerService(
				String qualification,
				DtoRecord_RegistryOfManager managerRecord,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		if (!personRepository.existsById(managerRecord.person_Id()))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		var managerClassDto = new DtoClass_RegistryOfManager(managerRecord);
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
		
		return new DtoRecord_OutputManagerOfRegistry_With_Uri(
					new DtoClass_OutputManagerOfRegistry(person, managerEmployee),
					uri);
	}
}
