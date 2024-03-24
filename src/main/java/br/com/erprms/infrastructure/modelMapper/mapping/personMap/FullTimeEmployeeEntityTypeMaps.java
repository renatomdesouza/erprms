package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeToListing;

@Configuration
public class FullTimeEmployeeEntityTypeMaps {
	private ModelMapper mapper; 
	
	public FullTimeEmployeeEntityTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean
	public void callFullTimeEmployeeEntityMaps() {
		dtoClassToManagerRegistry_To_FullTimeEmployeePersonQualificationSubclass(mapper);
		fullTimeEmployeePersonQualification_To_DtoClass_FullTimeEmployeeToListing(mapper);
	}
	
	public void dtoClassToManagerRegistry_To_FullTimeEmployeePersonQualificationSubclass(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(DtoClass_FullTimeEmployeeRegistry.class, FullTimeEmployeePersonQualification.class)
			.addMapping(DtoClass_FullTimeEmployeeRegistry::getMonthlySalary, FullTimeEmployeePersonQualification::setMonthlySalary )
			.addMapping(DtoClass_FullTimeEmployeeRegistry::getSector, FullTimeEmployeePersonQualification::setSector)
			.addMapping(DtoClass_FullTimeEmployeeRegistry::getObservation, FullTimeEmployeePersonQualification::setObservation);
	}
	
	public void fullTimeEmployeePersonQualification_To_DtoClass_FullTimeEmployeeToListing(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(FullTimeEmployeePersonQualification.class, DtoClass_FullTimeEmployeeToListing.class)
			.addMapping(FullTimeEmployeePersonQualification::getId, DtoClass_FullTimeEmployeeToListing::setId )
			.addMapping((ori) -> ori.getPerson().getId(), DtoClass_FullTimeEmployeeToListing::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), DtoClass_FullTimeEmployeeToListing::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), DtoClass_FullTimeEmployeeToListing::setPersonCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), DtoClass_FullTimeEmployeeToListing::setSector)
			.addMapping((ori) -> ori.getMonthlySalary(), DtoClass_FullTimeEmployeeToListing::setSalary)
			.addMapping((ori) -> ori.getObservation(), DtoClass_FullTimeEmployeeToListing::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), DtoClass_FullTimeEmployeeToListing::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), DtoClass_FullTimeEmployeeToListing::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), DtoClass_FullTimeEmployeeToListing::setFinalDate);
	}
	
	
}




