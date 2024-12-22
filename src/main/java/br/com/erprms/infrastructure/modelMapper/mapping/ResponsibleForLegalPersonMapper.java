package br.com.erprms.infrastructure.modelMapper.mapping;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.InputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.OutputDtoClassPage_ResponsibleForLegalPerson;

public class ResponsibleForLegalPersonMapper {
	
	public ResponsibleForLegalPersonMapper(ModelMapper mapper) {
		responsibleForLegalPersonMapper(mapper);
	}
	
	private void responsibleForLegalPersonMapper(ModelMapper mapper) {
		mapper.createTypeMap(InputDtoClass_ResponsibleForLegalPerson.class, ResponsibleForLegalPersonQualification.class)
        	.addMapping(InputDtoClass_ResponsibleForLegalPerson::getObservation, ResponsibleForLegalPersonQualification::setObservation);

	    mapper.createTypeMap(ResponsibleForLegalPersonQualification.class, OutputDtoClassPage_ResponsibleForLegalPerson.class)
	        .addMapping(PersonQualificationSuperclassEntity ::getId, OutputDtoClassPage_ResponsibleForLegalPerson::setId)
	        .addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_ResponsibleForLegalPerson::setPersonId)
	        .addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_ResponsibleForLegalPerson::setPersonName)
	        .addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputDtoClassPage_ResponsibleForLegalPerson::setCpfOrCnpj)
	        .addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_ResponsibleForLegalPerson::setObservation)
	        .addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_ResponsibleForLegalPerson::setInitialDate)
	        .addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_ResponsibleForLegalPerson::setFinalDate)
	        .<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (RESPONSIBLE_FOR_LEGAL_PERSON), OutputDtoClassPage_ResponsibleForLegalPerson::setSpecifiedQualification);
	}

}
