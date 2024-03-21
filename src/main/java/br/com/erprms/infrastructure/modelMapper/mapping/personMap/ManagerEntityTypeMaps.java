package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClass_RegistryOfManager;

@Configuration
public class ManagerEntityTypeMaps {
	private ModelMapper mapper; 
		
	public ManagerEntityTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean
	public void callManagerEntityMaps() {
		dtoClassToManagerRegistry_To_ManagerEmployeePersonQualificationSubclass(mapper);
	}
	
	public void dtoClassToManagerRegistry_To_ManagerEmployeePersonQualificationSubclass(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(DtoClass_RegistryOfManager.class, ManagerEmployeePersonQualificationSubclass.class)
			.addMapping(DtoClass_RegistryOfManager::getMonthlySalary, ManagerEmployeePersonQualificationSubclass::setMonthlySalary )
			.addMapping(DtoClass_RegistryOfManager::getSector, ManagerEmployeePersonQualificationSubclass::setSector)
			.addMapping(DtoClass_RegistryOfManager::getObservation, ManagerEmployeePersonQualificationSubclass::setObservation);
	}
}
