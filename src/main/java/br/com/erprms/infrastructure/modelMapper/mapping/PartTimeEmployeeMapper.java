package br.com.erprms.infrastructure.modelMapper.mapping;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PART_TIME_EMPLOYEE;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.OutputDtoClassPage_PartTimeEmployee;

public class PartTimeEmployeeMapper {
	
	public PartTimeEmployeeMapper(ModelMapper mapper) {
		partTimeEmployeeMapper(mapper);
	}
	
	private void partTimeEmployeeMapper(ModelMapper mapper) {
		mapper.createTypeMap(InputDtoClass_PartTimeEmployee.class, PartTimeEmployeePersonQualification.class)
			.addMapping(InputDtoClass_PartTimeEmployee::getHourlyRate, PartTimeEmployeePersonQualification::setHourlyRate )
			.addMapping(InputDtoClass_PartTimeEmployee::getSector, PartTimeEmployeePersonQualification::setSector)
			.addMapping(InputDtoClass_PartTimeEmployee::getObservation, PartTimeEmployeePersonQualification::setObservation)
			.addMapping(InputDtoClass_PartTimeEmployee::getProfessionalRegistry, PartTimeEmployeePersonQualification::setProfessionalRegistry);

		mapper.createTypeMap(PartTimeEmployeePersonQualification.class, OutputDtoClassPage_PartTimeEmployee.class)
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
