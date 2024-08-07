package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClassPage_FullTimeEmployeeAndManager;

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
		modelMapper.createTypeMap(ManagerPersonQualification.class, OutputDtoClassPage_FullTimeEmployeeAndManager.class)
			.addMapping((ori) -> ori.getId(), OutputDtoClassPage_FullTimeEmployeeAndManager::setId)
			.addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_FullTimeEmployeeAndManager::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_FullTimeEmployeeAndManager::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputDtoClassPage_FullTimeEmployeeAndManager::setCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), OutputDtoClassPage_FullTimeEmployeeAndManager::setSector)
			.addMapping((ori) -> ori.getMonthlySalary(), OutputDtoClassPage_FullTimeEmployeeAndManager::setSalary)
			.addMapping((ori) -> ori.getObservation(), OutputDtoClassPage_FullTimeEmployeeAndManager::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), OutputDtoClassPage_FullTimeEmployeeAndManager::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), OutputDtoClassPage_FullTimeEmployeeAndManager::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), OutputDtoClassPage_FullTimeEmployeeAndManager::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (MANAGER), (dest, s) -> dest.setSpecifiedQualification(s));
	}
}
