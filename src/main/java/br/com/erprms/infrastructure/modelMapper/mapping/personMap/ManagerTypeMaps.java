package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputPageDtoClass_FullTimeEmployeeAndManager;

@Configuration
public class ManagerTypeMaps {
	private final ModelMapper mapper; 
		
	public ManagerTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean
	public void callManagerEntityMaps() {
		dtoClassToManagerRegistry_To_ManagerEmployeePersonQualificationSubclass(mapper);
		managerPersonQualification_To_DtoClass_FullTimeEmployeeToListing(mapper);
	}
	
	private void dtoClassToManagerRegistry_To_ManagerEmployeePersonQualificationSubclass(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(InputDtoClass_FullTimeEmployeeAndManager.class, ManagerPersonQualification.class)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getMonthlySalary, ManagerPersonQualification::setMonthlySalary )
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getSector, ManagerPersonQualification::setSector)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getObservation, ManagerPersonQualification::setObservation)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getProfessionalRegistry, ManagerPersonQualification::setProfessionalRegistry);
	}
	
	private void managerPersonQualification_To_DtoClass_FullTimeEmployeeToListing(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(ManagerPersonQualification.class, OutputPageDtoClass_FullTimeEmployeeAndManager.class)
			.addMapping((ori) -> ori.getId(), OutputPageDtoClass_FullTimeEmployeeAndManager::setId)
			.addMapping((ori) -> ori.getPerson().getId(), OutputPageDtoClass_FullTimeEmployeeAndManager::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputPageDtoClass_FullTimeEmployeeAndManager::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputPageDtoClass_FullTimeEmployeeAndManager::setCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), OutputPageDtoClass_FullTimeEmployeeAndManager::setSector)
			.addMapping((ori) -> ori.getMonthlySalary(), OutputPageDtoClass_FullTimeEmployeeAndManager::setSalary)
			.addMapping((ori) -> ori.getObservation(), OutputPageDtoClass_FullTimeEmployeeAndManager::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), OutputPageDtoClass_FullTimeEmployeeAndManager::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), OutputPageDtoClass_FullTimeEmployeeAndManager::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), OutputPageDtoClass_FullTimeEmployeeAndManager::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (MANAGER), (dest, s) -> dest.setSpecifiedQualification(s));
	}
}
