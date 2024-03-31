package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PartTimeEmployeeDto.DtoClass_PartTimeEmployeeToListing;

@Configuration
public class PartTimeEmployeeTypeMaps {
private final ModelMapper mapper; 
	
	public PartTimeEmployeeTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean
	public void callPartTimeEmployeeEntityMaps() {
		dtoClassToManagerRegistry_To_FullTimeEmployeePersonQualificationSubclass(mapper);
		partTimeEmployeePersonQualification_To_DtoClass_PartyTimeEmployeeToListing(mapper);
	}
	
	private void dtoClassToManagerRegistry_To_FullTimeEmployeePersonQualificationSubclass(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(DtoClass_PartTimeEmployeeRegistry.class, PartTimeEmployeePersonQualification.class)
			.addMapping(DtoClass_PartTimeEmployeeRegistry::getHourlyRate, PartTimeEmployeePersonQualification::setHourlyRate )
			.addMapping(DtoClass_PartTimeEmployeeRegistry::getSector, PartTimeEmployeePersonQualification::setSector)
			.addMapping(DtoClass_PartTimeEmployeeRegistry::getObservation, PartTimeEmployeePersonQualification::setObservation)
			.addMapping(DtoClass_PartTimeEmployeeRegistry::getProfessionalRegistry, PartTimeEmployeePersonQualification::setProfessionalRegistry);
	}
	
	private void partTimeEmployeePersonQualification_To_DtoClass_PartyTimeEmployeeToListing(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(PartTimeEmployeePersonQualification.class, DtoClass_PartTimeEmployeeToListing.class)
			.addMapping((ori) -> ori.getId(), DtoClass_PartTimeEmployeeToListing::setId)
			.addMapping((ori) -> ori.getPerson().getId(), DtoClass_PartTimeEmployeeToListing::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), DtoClass_PartTimeEmployeeToListing::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), DtoClass_PartTimeEmployeeToListing::setPersonCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), DtoClass_PartTimeEmployeeToListing::setSector)
			.addMapping((ori) -> ori.getHourlyRate(), DtoClass_PartTimeEmployeeToListing::setHourlyRate)
			.addMapping((ori) -> ori.getObservation(), DtoClass_PartTimeEmployeeToListing::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), DtoClass_PartTimeEmployeeToListing::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), DtoClass_PartTimeEmployeeToListing::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), DtoClass_PartTimeEmployeeToListing::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (PART_TIME_EMPLOYEE), (dest, s) -> dest.setSpecifiedQualification(s));
	}
}
