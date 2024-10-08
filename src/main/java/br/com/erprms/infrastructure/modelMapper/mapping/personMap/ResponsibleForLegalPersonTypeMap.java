package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.InputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.OutputDtoClassPage_ResponsibleForLegalPerson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

@Configuration
public class ResponsibleForLegalPersonTypeMap {
	   private final ModelMapper mapper;

	    public ResponsibleForLegalPersonTypeMap(ModelMapper mapper) {
	        this.mapper = mapper;
	    }

	    @Bean
	    public void callResponsibleForLegalPersonEntityMapsBean() {
	        inputDtoClass_ResponsibleForLegalPerson_To_ResponsibleForLegalPersonQualification(mapper);
	        responsibleForLegalPersonQualification_To_OutputDtoClassPage_ResponsibleForLegalPerson(mapper);
	    }

	    public void inputDtoClass_ResponsibleForLegalPerson_To_ResponsibleForLegalPersonQualification(ModelMapper modelMapper) {
	        modelMapper.createTypeMap(InputDtoClass_ResponsibleForLegalPerson.class, ResponsibleForLegalPersonQualification.class)
	                .addMapping(InputDtoClass_ResponsibleForLegalPerson::getObservation, ResponsibleForLegalPersonQualification::setObservation);
	    }

	    private void responsibleForLegalPersonQualification_To_OutputDtoClassPage_ResponsibleForLegalPerson(ModelMapper modelMapper) {
	        modelMapper.createTypeMap(ResponsibleForLegalPersonQualification.class, OutputDtoClassPage_ResponsibleForLegalPerson.class)
	                .addMapping(PersonQualificationSuperclassEntity ::getId, OutputDtoClassPage_ResponsibleForLegalPerson::setId)
	                .addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_ResponsibleForLegalPerson::setPersonId)
	                .addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_ResponsibleForLegalPerson::setPersonName)
	                .addMapping((ori) -> ori.getPerson().getCpfCnpj(), OutputDtoClassPage_ResponsibleForLegalPerson::setCpfOrCnpj)
	                .addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_ResponsibleForLegalPerson::setObservation)
	                .addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_ResponsibleForLegalPerson::setInitialDate)
	                .addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_ResponsibleForLegalPerson::setFinalDate)
	                .<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (RESPONSIBLE_FOR_LEGAL_PERSON), OutputDtoClassPage_ResponsibleForLegalPerson::setSpecifiedQualification);
	    }
}
