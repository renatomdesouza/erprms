package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.OutputDtoClassPage_PartTimeEmployee;

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
		modelMapper.createTypeMap(InputDtoClass_PartTimeEmployee.class, PartTimeEmployeePersonQualification.class)
			.addMapping(InputDtoClass_PartTimeEmployee::getHourlyRate, PartTimeEmployeePersonQualification::setHourlyRate )
			.addMapping(InputDtoClass_PartTimeEmployee::getSector, PartTimeEmployeePersonQualification::setSector)
			.addMapping(InputDtoClass_PartTimeEmployee::getObservation, PartTimeEmployeePersonQualification::setObservation)
			.addMapping(InputDtoClass_PartTimeEmployee::getProfessionalRegistry, PartTimeEmployeePersonQualification::setProfessionalRegistry);
	}
	
	private void partTimeEmployeePersonQualification_To_DtoClass_PartyTimeEmployeeToListing(ModelMapper modelMapper) {   
		modelMapper.createTypeMap(PartTimeEmployeePersonQualification.class, OutputDtoClassPage_PartTimeEmployee.class)
			.addMapping((ori) -> ori.getId(), OutputDtoClassPage_PartTimeEmployee::setId)
			.addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_PartTimeEmployee::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_PartTimeEmployee::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputDtoClassPage_PartTimeEmployee::setCpfOrCnpj)
			.addMapping((ori) -> ori.getSector(), OutputDtoClassPage_PartTimeEmployee::setSector)
			.addMapping((ori) -> ori.getHourlyRate(), OutputDtoClassPage_PartTimeEmployee::setHourlyRate)
			.addMapping((ori) -> ori.getObservation(), OutputDtoClassPage_PartTimeEmployee::setObservation)
			.addMapping((ori) -> ori.getProfessionalRegistry(), OutputDtoClassPage_PartTimeEmployee::setProfessionalRegistry)
			.addMapping((ori) -> ori.getInitialDate(), OutputDtoClassPage_PartTimeEmployee::setInitialDate)
			.addMapping((ori) -> ori.getFinalDate(), OutputDtoClassPage_PartTimeEmployee::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (PART_TIME_EMPLOYEE), (dest, s) -> dest.setSpecifiedQualification(s));
	}
}
