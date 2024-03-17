package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.employeePersonQualificatorInheritor.ManagerEmployeePersonQualificationSubclass;
import br.com.erprms.dtoPort.personDto.managerDto.DtoClassToRegistryOfManager;

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
		modelMapper.createTypeMap(DtoClassToRegistryOfManager.class, ManagerEmployeePersonQualificationSubclass.class)
			.addMapping(DtoClassToRegistryOfManager::getSalary, ManagerEmployeePersonQualificationSubclass::setSalary )
			.addMapping(DtoClassToRegistryOfManager::getSector, ManagerEmployeePersonQualificationSubclass::setSector)
			.addMapping(DtoClassToRegistryOfManager::getObservation, ManagerEmployeePersonQualificationSubclass::setObservation);
	}

}
