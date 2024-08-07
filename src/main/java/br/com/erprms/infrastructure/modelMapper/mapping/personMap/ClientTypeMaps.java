package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.CLIENT;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ClientPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.InputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.OutputDtoClassPage_Client;

@Configuration
public class ClientTypeMaps {
    private final ModelMapper mapper;

    public ClientTypeMaps(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Bean
    public void callClientEntityMapsBean() {
        inputDtoClass_Client_To_ClientPersonQualification(mapper);
        clientPersonQualification_To_OutputDtoClassPage_Client(mapper);
    }

    public void inputDtoClass_Client_To_ClientPersonQualification(ModelMapper modelMapper) {
        modelMapper.createTypeMap(InputDtoClass_Client.class, ClientPersonQualification.class)
                .addMapping(InputDtoClass_Client::getCreditTerms, ClientPersonQualification::setCreditTerms)
                .addMapping(InputDtoClass_Client::getObservation, ClientPersonQualification::setObservation);
    }

    private void clientPersonQualification_To_OutputDtoClassPage_Client(ModelMapper modelMapper) {
        modelMapper.createTypeMap(ClientPersonQualification.class, OutputDtoClassPage_Client.class)
                .addMapping(PersonQualificationSuperclassEntity::getId, OutputDtoClassPage_Client::setId)
                .addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_Client::setPersonId)
                .addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_Client::setPersonName)
                .addMapping((ori) -> ori.getPerson().getCpfOrCnpj(), OutputDtoClassPage_Client::setCpfOrCnpj)
                .addMapping((ori) -> ori.getCreditTerms(), OutputDtoClassPage_Client::setCreditDays)
                .addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_Client::setObservation)
                .addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_Client::setInitialDate)
                .addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_Client::setFinalDate)
                .<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (CLIENT), OutputDtoClassPage_Client::setSpecifiedQualification);
    }
}
