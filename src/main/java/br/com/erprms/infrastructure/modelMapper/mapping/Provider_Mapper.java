package br.com.erprms.infrastructure.modelMapper.mapping;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.InputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.OutputDtoClassPage_Provider;

public class Provider_Mapper {
	
	public Provider_Mapper(ModelMapper mapper) {
		providerMapper(mapper);
	}
	
	private void providerMapper(ModelMapper mapper) {
		mapper.createTypeMap(InputDtoClass_Provider.class, ProviderPersonQualification.class)
        	.addMapping(InputDtoClass_Provider::getObservation, ProviderPersonQualification::setObservation);

		mapper.createTypeMap(ProviderPersonQualification.class, OutputDtoClassPage_Provider.class)
	        .addMapping(PersonQualificationSuperclassEntity::getId, OutputDtoClassPage_Provider::setId)
	        .addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_Provider::setPersonId)
	        .addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_Provider::setPersonName)
	        .addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputDtoClassPage_Provider::setCpfOrCnpj)
	        .addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_Provider::setObservation)
	        .addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_Provider::setInitialDate)
	        .addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_Provider::setFinalDate)
	        .<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (PROVIDER), OutputDtoClassPage_Provider::setSpecifiedQualification);
	}

}
