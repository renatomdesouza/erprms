package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataInputDto.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.DataOutPutDto.OutputPageDtoClass_FullTimeEmployeeAndManager;

@Configuration
public class FullTimeEmployeeTypeMaps {
	private final ModelMapper mapper; 
	
	public FullTimeEmployeeTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	@Bean
	public void callFullTimeEmployeeEntityMaps() {
		dtoClassToManagerRegistry_To_FullTimeEmployeePersonQualificationSubclass(mapper);
		fullTimeEmployeePersonQualification_To_DtoClass_FullTimeEmployeeToListing(mapper);
	}
	
	private void dtoClassToManagerRegistry_To_FullTimeEmployeePersonQualificationSubclass(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(InputDtoClass_FullTimeEmployeeAndManager.class, FullTimeEmployeePersonQualification.class)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getMonthlySalary, FullTimeEmployeePersonQualification::setMonthlySalary )
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getSector, FullTimeEmployeePersonQualification::setSector)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getObservation, FullTimeEmployeePersonQualification::setObservation)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getProfessionalRegistry, FullTimeEmployeePersonQualification::setProfessionalRegistry);
	}
	
	private void fullTimeEmployeePersonQualification_To_DtoClass_FullTimeEmployeeToListing(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(FullTimeEmployeePersonQualification.class, OutputPageDtoClass_FullTimeEmployeeAndManager.class)
			.addMapping((ori) -> ori.getId(), OutputPageDtoClass_FullTimeEmployeeAndManager::setId)
			.addMapping((ori) -> ori.getPerson().getId(), OutputPageDtoClass_FullTimeEmployeeAndManager::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputPageDtoClass_FullTimeEmployeeAndManager::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputPageDtoClass_FullTimeEmployeeAndManager::setPersonCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), OutputPageDtoClass_FullTimeEmployeeAndManager::setSector)
			.addMapping((ori) -> ori.getMonthlySalary(), OutputPageDtoClass_FullTimeEmployeeAndManager::setSalary)
			.addMapping((ori) -> ori.getObservation(), OutputPageDtoClass_FullTimeEmployeeAndManager::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), OutputPageDtoClass_FullTimeEmployeeAndManager::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), OutputPageDtoClass_FullTimeEmployeeAndManager::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), OutputPageDtoClass_FullTimeEmployeeAndManager::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (FULL_TIME_EMPLOYEE), (dest, s) -> dest.setSpecifiedQualification(s));
	}
}




