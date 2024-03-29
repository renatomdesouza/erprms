package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.MANAGER;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeToListing;

@Configuration
public class ManagerEntityTypeMaps {
	private final ModelMapper mapper; 
		
	public ManagerEntityTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean
	public void callManagerEntityMaps() {
		dtoClassToManagerRegistry_To_ManagerEmployeePersonQualificationSubclass(mapper);
		managerPersonQualification_To_DtoClass_FullTimeEmployeeToListing(mapper);
	}
	
	private void dtoClassToManagerRegistry_To_ManagerEmployeePersonQualificationSubclass(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(DtoClass_ManagerAndFullTimeEmployeeRegistry.class, ManagerPersonQualification.class)
			.addMapping(DtoClass_ManagerAndFullTimeEmployeeRegistry::getMonthlySalary, ManagerPersonQualification::setMonthlySalary )
			.addMapping(DtoClass_ManagerAndFullTimeEmployeeRegistry::getSector, ManagerPersonQualification::setSector)
			.addMapping(DtoClass_ManagerAndFullTimeEmployeeRegistry::getObservation, ManagerPersonQualification::setObservation)
			.addMapping(DtoClass_ManagerAndFullTimeEmployeeRegistry::getProfessionalRegistry, ManagerPersonQualification::setProfessionalRegistry);
	}
	
	private void managerPersonQualification_To_DtoClass_FullTimeEmployeeToListing(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(ManagerPersonQualification.class, DtoClass_ManagerAndFullTimeEmployeeToListing.class)
			.addMapping((ori) -> ori.getId(), DtoClass_ManagerAndFullTimeEmployeeToListing::setId)
			.addMapping((ori) -> ori.getPerson().getId(), DtoClass_ManagerAndFullTimeEmployeeToListing::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), DtoClass_ManagerAndFullTimeEmployeeToListing::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), DtoClass_ManagerAndFullTimeEmployeeToListing::setPersonCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), DtoClass_ManagerAndFullTimeEmployeeToListing::setSector)
			.addMapping((ori) -> ori.getMonthlySalary(), DtoClass_ManagerAndFullTimeEmployeeToListing::setSalary)
			.addMapping((ori) -> ori.getObservation(), DtoClass_ManagerAndFullTimeEmployeeToListing::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), DtoClass_ManagerAndFullTimeEmployeeToListing::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), DtoClass_ManagerAndFullTimeEmployeeToListing::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), DtoClass_ManagerAndFullTimeEmployeeToListing::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (MANAGER), (dest, s) -> dest.setSpecifiedQualification(s));
	}
}
