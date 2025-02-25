package br.com.erprms.infrastructure.modelMapper.mapping;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.FULL_TIME_EMPLOYEE;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.OutputDtoClassPage_FullTimeEmployeeAndManager;

public class FullTimeEmployeeAndManageMapper {

	public FullTimeEmployeeAndManageMapper(ModelMapper mapper) {
		fullTimeEmployeeAndManageMapper(mapper);
	}
	
	private void fullTimeEmployeeAndManageMapper(ModelMapper mapper) {
		mapper.createTypeMap(InputDtoClass_FullTimeEmployeeAndManager.class, FullTimeEmployeePersonQualification.class)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getMonthlySalary, FullTimeEmployeePersonQualification::setMonthlySalary )
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getSector, FullTimeEmployeePersonQualification::setSector)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getObservation, FullTimeEmployeePersonQualification::setObservation)
			.addMapping(InputDtoClass_FullTimeEmployeeAndManager::getProfessionalRegistry, FullTimeEmployeePersonQualification::setProfessionalRegistry);

		mapper.createTypeMap(FullTimeEmployeePersonQualification.class, OutputDtoClassPage_FullTimeEmployeeAndManager.class)
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
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (FULL_TIME_EMPLOYEE), (dest, s) -> dest.setSpecifiedQualification(s));
	}
	
}
