package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerEmployeeRegistryOutput;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoRecord_FullTimeEmployeeRegistry;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonQualificationRepository;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class ManagerService {
	private ModelMapper mapper;
	private PersonRepository personRepository;
	private ManagerRepository managerRepository;
	private GeneralExclude_PersonQualificationService genereralExclude;
	
	public ManagerService(
			ModelMapper mapper, 
			PersonRepository personRepository, 
			ManagerRepository managerRepository, 
			GeneralExclude_PersonQualificationService genereralExclude) {
		this.mapper = mapper;
		this.personRepository = personRepository;
		this.managerRepository = managerRepository;
		this.genereralExclude = genereralExclude;
	}

	@Autowired
	private PersonQualificationRepository personQualificationRepository;

	@Transactional
	@SuppressWarnings("null")
	public DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri registerService(
				DtoRecord_FullTimeEmployeeRegistry fullTimeManagerRecordDto,
				UriComponentsBuilder uriComponentsBuilder) 
				throws ResponseStatusException {
		if (!personRepository.existsById(fullTimeManagerRecordDto.person_Id()))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"There is no \"Person\" registered with this \"Id\"");
		
		var fullTimeManagerClassDto = new DtoClass_FullTimeEmployeeRegistry(fullTimeManagerRecordDto);

		var personEntity = personRepository.getReferenceById(fullTimeManagerClassDto.getPerson_Id());
		
		var managerEntity = mapper.map(fullTimeManagerClassDto, ManagerPersonQualification.class);

		if (managerRepository.existsManagerEmployeePersonQualificationByFinalDateIsNullAndPerson(personEntity))
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE, 
					"An active \"Manager\" registry already exists for this Person");

		managerEntity.setPerson(personEntity);
		managerEntity.setInitialDate(LocalDate.now());
		managerRepository.save(managerEntity);
		
		var uri = uriComponentsBuilder
					.path("/manager/{id}")
					.buildAndExpand(managerEntity.getId())
					.toUri();
		
//		return new DtoRecord_FullTimeEmployeeOutputRegistry_With_Uri(
//					new DtoClass_ManagerEmployeeRegistryOutput(personEntity, managerEntity),
//					uri);
		return null;
	}
	
	@Transactional   
	public Page<DtoClass_FullTimeEmployeeListing> listingService(Pageable qualificationPageable) {  
		return managerRepository
				.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(DtoClass_FullTimeEmployeeListing::new);
	}

	@Transactional
	public void exclude(Long person_Id, String specifiedQualification) {
		genereralExclude.generalExclude(person_Id, specifiedQualification);
	}
	
}
