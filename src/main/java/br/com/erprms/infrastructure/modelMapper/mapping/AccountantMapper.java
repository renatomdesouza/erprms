package br.com.erprms.infrastructure.modelMapper.mapping;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.OutputDtoClassPage_Accountant;

public class AccountantMapper {

	public AccountantMapper(ModelMapper mapper) {
		accountantMapper(mapper);
	}
	
	private void accountantMapper(ModelMapper mapper) {
		mapper.createTypeMap(InputDtoClass_Accountant.class, AccountantPersonQualification.class)
			.addMapping(InputDtoClass_Accountant::getMonthlyCost, AccountantPersonQualification::setMonthlyCost )
			.addMapping(InputDtoClass_Accountant::getObservation, AccountantPersonQualification::setObservation)
			.addMapping(InputDtoClass_Accountant::getProfessionalRegistry, AccountantPersonQualification::setProfessionalRegistry);
    	
    	mapper.createTypeMap(AccountantPersonQualification.class, OutputDtoClassPage_Accountant.class)
			.addMapping(PersonQualificationSuperclassEntity::getId, OutputDtoClassPage_Accountant::setId)
			.addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_Accountant::setPersonId)
			.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_Accountant::setPersonName)
			.addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputDtoClassPage_Accountant::setCpfOrCnpj)
			.addMapping(AccountantPersonQualification::getMonthlyCost, OutputDtoClassPage_Accountant::setMonthlyCost)
			.addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_Accountant::setObservation)
			.addMapping(PersonQualificationSuperclassEntity::getProfessionalRegistry, OutputDtoClassPage_Accountant::setProfessionalRegistry)
			.addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_Accountant::setInitialDate)
			.addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_Accountant::setFinalDate)
			.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (ACCOUNTANT), (dest, s) -> dest.setSpecifiedQualification(s));
	}
	
}
