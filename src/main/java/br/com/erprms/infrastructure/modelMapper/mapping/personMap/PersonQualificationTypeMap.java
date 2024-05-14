package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonQualificationTypeMap {
    private final ModelMapper mapper;

    public PersonQualificationTypeMap(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Bean
    public void callPersonQualificationMaps() {
        personQualificationSuperclassEntity_To_PersonQualificationSuperclassEntity(mapper);
    }

    public void personQualificationSuperclassEntity_To_PersonQualificationSuperclassEntity(ModelMapper modelMapper) {
        modelMapper.createTypeMap(PersonQualificationSuperclassEntity.class, PersonQualificationSuperclassEntity.class)
                .addMapping(PersonQualificationSuperclassEntity::getFinalDate, PersonQualificationSuperclassEntity::setFinalDate )
                .addMapping(PersonQualificationSuperclassEntity::getInitialDate, PersonQualificationSuperclassEntity::setInitialDate)
                .addMapping(PersonQualificationSuperclassEntity::getHttpVerb, PersonQualificationSuperclassEntity::setHttpVerb)
                .addMapping(PersonQualificationSuperclassEntity::getPerson, PersonQualificationSuperclassEntity::setPerson)
                .addMapping(PersonQualificationSuperclassEntity::getProfessionalRegistry, PersonQualificationSuperclassEntity::setProfessionalRegistry)
                .addMapping(PersonQualificationSuperclassEntity::getIsActual, PersonQualificationSuperclassEntity::setIsActual)
                .addMapping(PersonQualificationSuperclassEntity::getLoginUser, PersonQualificationSuperclassEntity::setLoginUser)
                .addMapping(PersonQualificationSuperclassEntity::getObservation, PersonQualificationSuperclassEntity::setObservation)
                .addMapping(PersonQualificationSuperclassEntity::getId, PersonQualificationSuperclassEntity::setPreviousRecord);
    }
}
