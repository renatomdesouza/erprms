package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClassToLegalPersonOfListing;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfUpdate;

@Configuration
public class LegalPersonTypeMaps {
	private ModelMapper mapper; 
	
	public LegalPersonTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Bean
	public void callPessJuridicEntityMaps() {
		dtoClassToLegalPersonOfRegistry_To_PersonEntity_TypeMap(mapper);
		personEntity_To_dtoClassToLegalPersonOfListing_TypeMap(mapper);
		dtoClassToLegalPersonOfUpdate_To_PersonEntity_TypeMap(mapper);
	}
	
	public void dtoClassToLegalPersonOfRegistry_To_PersonEntity_TypeMap(ModelMapper mapper) {
		mapper.createTypeMap(DtoClass_LegalPersonOfRegistry.class, PersonEntity.class)
			.addMapping(DtoClass_LegalPersonOfRegistry::getFullNameOrEntityName, PersonEntity::setFullNameOrEntityName)
			.addMapping(DtoClass_LegalPersonOfRegistry::getNickname, PersonEntity::setNickname)
			.addMapping(DtoClass_LegalPersonOfRegistry::getCnpj, PersonEntity::setCpfOrCnpj)
			.addMapping(DtoClass_LegalPersonOfRegistry::getEmail, PersonEntity::setEmail)
			.addMapping(DtoClass_LegalPersonOfRegistry::getInscricEstad, PersonEntity::setInscricEstad)
			.addMapping(DtoClass_LegalPersonOfRegistry::getInscricMunicip, PersonEntity::setInscricMunicip)
			.addMapping(DtoClass_LegalPersonOfRegistry::getSite, PersonEntity::setSite)
			.addMapping(DtoClass_LegalPersonOfRegistry::getStreet, PersonEntity::setStreet)
			.addMapping(DtoClass_LegalPersonOfRegistry::getNumber, PersonEntity::setNumber)
			.addMapping(DtoClass_LegalPersonOfRegistry::getNeighborhood, PersonEntity::setNeighborhood)
			.addMapping(DtoClass_LegalPersonOfRegistry::getComplement, PersonEntity::setComplement)
			.addMapping(DtoClass_LegalPersonOfRegistry::getPostalCode, PersonEntity::setPostalCode)
			.addMapping(DtoClass_LegalPersonOfRegistry::getCityAndStateOrProvince, PersonEntity::setCityAndStateOrProvince)
			.addMapping(DtoClass_LegalPersonOfRegistry::getStatusPersonEnum, PersonEntity::setStatusPersonEnum)
			.addMapping(DtoClass_LegalPersonOfRegistry::getIsNaturalPerson, PersonEntity::setIsNaturalPerson);
	}
	
	public void personEntity_To_dtoClassToLegalPersonOfListing_TypeMap(ModelMapper mapper) {
		mapper.createTypeMap(PersonEntity.class, DtoClassToLegalPersonOfListing.class)
			.addMapping(PersonEntity::getId, DtoClassToLegalPersonOfListing::setId)
			.addMapping(PersonEntity::getFullNameOrEntityName, DtoClassToLegalPersonOfListing::setFullNameOrEntityName)
			.addMapping(PersonEntity::getNickname, DtoClassToLegalPersonOfListing::setNickname)
			.addMapping(PersonEntity::getStatusPersonEnum, DtoClassToLegalPersonOfListing::setStatusPersonEnum)
			.addMapping(PersonEntity::getCpfOrCnpj, DtoClassToLegalPersonOfListing::setCpfOrCnpj)
			.addMapping(PersonEntity::getInscricEstad, DtoClassToLegalPersonOfListing::setInscricEstad)
			.addMapping(PersonEntity::getInscricMunicip, DtoClassToLegalPersonOfListing::setInscricMunicip)
			.addMapping(PersonEntity::getEmail, DtoClassToLegalPersonOfListing::setEmail)
			.addMapping(PersonEntity::getSite, DtoClassToLegalPersonOfListing::setSite)
			.addMapping(PersonEntity::getStreet, DtoClassToLegalPersonOfListing::setStreet)
			.addMapping(PersonEntity::getNumber, DtoClassToLegalPersonOfListing::setNumber)
			.addMapping(PersonEntity::getNeighborhood, DtoClassToLegalPersonOfListing::setNeighborhood)
			.addMapping(PersonEntity::getComplement, DtoClassToLegalPersonOfListing::setComplement)
			.addMapping(PersonEntity::getPostalCode, DtoClassToLegalPersonOfListing::setPostalCode)
			.addMapping(PersonEntity::getCityAndStateOrProvince, DtoClassToLegalPersonOfListing::setCityAndStateOrProvince);
	}

	public void dtoClassToLegalPersonOfUpdate_To_PersonEntity_TypeMap(ModelMapper mapper) {
		mapper.createTypeMap(DtoClass_LegalPersonOfUpdate.class, PersonEntity.class)
			.addMapping(DtoClass_LegalPersonOfUpdate::getId, PersonEntity::setId)
			.addMapping(DtoClass_LegalPersonOfUpdate::getFullNameOrEntityName, PersonEntity::setFullNameOrEntityName)
			.addMapping(DtoClass_LegalPersonOfUpdate::getNickname, PersonEntity::setNickname)
			.addMapping(DtoClass_LegalPersonOfUpdate::getCnpj, PersonEntity::setCpfOrCnpj)
			.addMapping(DtoClass_LegalPersonOfUpdate::getEmail, PersonEntity::setEmail)
			.addMapping(DtoClass_LegalPersonOfUpdate::getSite, PersonEntity::setSite)
			.addMapping(DtoClass_LegalPersonOfUpdate::getStreet, PersonEntity::setStreet)
			.addMapping(DtoClass_LegalPersonOfUpdate::getNumber, PersonEntity::setNumber)
			.addMapping(DtoClass_LegalPersonOfUpdate::getNeighborhood, PersonEntity::setNeighborhood)
			.addMapping(DtoClass_LegalPersonOfUpdate::getComplement, PersonEntity::setComplement)
			.addMapping(DtoClass_LegalPersonOfUpdate::getPostalCode, PersonEntity::setPostalCode)
			.addMapping(DtoClass_LegalPersonOfUpdate::getCityStat, PersonEntity::setCityAndStateOrProvince);
	}
}
