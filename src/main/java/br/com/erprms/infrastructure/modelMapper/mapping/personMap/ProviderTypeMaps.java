package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.InputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.OutputDtoClassPage_Provider;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;

@Configuration
public class ProviderTypeMaps {
    private final ModelMapper mapper;

    public ProviderTypeMaps(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Bean
    public void callProviderEntityMapsBean() {
        inputDtoClass_Provider_To_ProviderPersonQualification(mapper);
        providerPersonQualification_To_OutputDtoClassPage_Provider(mapper);
    }

    public void inputDtoClass_Provider_To_ProviderPersonQualification(ModelMapper modelMapper) {
        modelMapper.createTypeMap(InputDtoClass_Provider.class, ProviderPersonQualification.class)
                .addMapping(InputDtoClass_Provider::getObservation, ProviderPersonQualification::setObservation);
    }

    private void providerPersonQualification_To_OutputDtoClassPage_Provider(ModelMapper modelMapper) {
        modelMapper.createTypeMap(ProviderPersonQualification.class, OutputDtoClassPage_Provider.class)
                .addMapping(PersonQualificationSuperclassEntity::getId, OutputDtoClassPage_Provider::setId)
                .addMapping((ori) -> ori.getPerson().getId(), OutputDtoClassPage_Provider::setPersonId)
                .addMapping((ori) -> ori.getPerson().getFullNameOrEntityName(), OutputDtoClassPage_Provider::setPersonName)
                .addMapping((ori) -> ori.getPerson().getCpfCnpj(), OutputDtoClassPage_Provider::setCpfOrCnpj)
                .addMapping(PersonQualificationSuperclassEntity::getObservation, OutputDtoClassPage_Provider::setObservation)
                .addMapping(PersonQualificationSuperclassEntity::getInitialDate, OutputDtoClassPage_Provider::setInitialDate)
                .addMapping(PersonQualificationSuperclassEntity::getFinalDate, OutputDtoClassPage_Provider::setFinalDate)
                .<String>addMapping(mappingByDefaultValueWithoutSourceClass -> (PROVIDER), OutputDtoClassPage_Provider::setSpecifiedQualification);
    }
}