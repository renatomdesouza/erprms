package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;

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
		modelMapper.createTypeMap(DtoClass_FullTimeEmployeeRegistry.class, ManagerEmployeePersonQualification.class)
			.addMapping(DtoClass_FullTimeEmployeeRegistry::getMonthlySalary, ManagerEmployeePersonQualification::setMonthlySalary )
			.addMapping(DtoClass_FullTimeEmployeeRegistry::getSector, ManagerEmployeePersonQualification::setSector)
			.addMapping(DtoClass_FullTimeEmployeeRegistry::getObservation, ManagerEmployeePersonQualification::setObservation);
	}
}
