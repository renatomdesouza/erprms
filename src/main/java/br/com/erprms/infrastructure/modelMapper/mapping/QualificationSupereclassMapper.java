package br.com.erprms.infrastructure.modelMapper.mapping;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;

public class QualificationSupereclassMapper {
	
	public QualificationSupereclassMapper(ModelMapper mapper) {
		qualificationSupereclassMapper(mapper);
	}
	
	private void qualificationSupereclassMapper(ModelMapper mapper) {
		mapper.createTypeMap(PersonQualificationSuperclassEntity.class, PersonQualificationSuperclassEntity.class)
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
