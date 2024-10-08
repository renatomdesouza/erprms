package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.OutputDtoClassPage_Accountant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;

@Configuration
public class AccountantTypeMaps {
	private final ModelMapper mapper;
	
	public AccountantTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Bean
	public void callAccountantEntityMaps() {
		inputDtoClass_Accountant_To_AccountantEntity(mapper);
		AccountantEntity_To_DtoClass_FullTimeEmployeeToListing(mapper);
	}

	public void inputDtoClass_Accountant_To_AccountantEntity(ModelMapper modelMapper) {
		modelMapper.createTypeMap(InputDtoClass_Accountant.class, AccountantPersonQualification.class)
			.addMapping(InputDtoClass_Accountant::getMonthlyCost, AccountantPersonQualification::setMonthlyCost )
			.addMapping(InputDtoClass_Accountant::getObservation, AccountantPersonQualification::setObservation)
			.addMapping(InputDtoClass_Accountant::getProfessionalRegistry, AccountantPersonQualification::setProfessionalRegistry);
	}

	private void AccountantEntity_To_DtoClass_FullTimeEmployeeToListing(ModelMapper modelMapper) {
		modelMapper.createTypeMap(AccountantPersonQualification.class, OutputDtoClassPage_Accountant.class)
				.addMapping(PersonQualificationSuperclassEntity::getId, OutputDtoClassPage_Accountant::setId)
				.addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_Accountant::setPersonId)
				.addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_Accountant::setPersonName)
				.addMapping((ori) -> ori.getPerson().getCpfCnpj(), OutputDtoClassPage_Accountant::setCpfOrCnpj)
				.addMapping(AccountantPersonQualification::getMonthlyCost, OutputDtoClassPage_Accountant::setMonthlyCost)
				.addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_Accountant::setObservation)
				.addMapping(PersonQualificationSuperclassEntity::getProfessionalRegistry, OutputDtoClassPage_Accountant::setProfessionalRegistry)
				.addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_Accountant::setInitialDate)
				.addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_Accountant::setFinalDate)
				.<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (ACCOUNTANT), (dest, s) -> dest.setSpecifiedQualification(s));
	}

}

