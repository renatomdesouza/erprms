package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfListing;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoClass_LegalPersonOfUpdate;

@Configuration
public class LegalPersonTypeMaps {
	private final ModelMapper mapper;
	
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
		mapper.createTypeMap(PersonEntity.class, DtoClass_LegalPersonOfListing.class)
			.addMapping(PersonEntity::getId, DtoClass_LegalPersonOfListing::setId)
			.addMapping(PersonEntity::getFullNameOrEntityName, DtoClass_LegalPersonOfListing::setFullNameOrEntityName)
			.addMapping(PersonEntity::getNickname, DtoClass_LegalPersonOfListing::setNickname)
			.addMapping(PersonEntity::getStatusPersonEnum, DtoClass_LegalPersonOfListing::setStatusPersonEnum)
			.addMapping(PersonEntity::getCpfOrCnpj, DtoClass_LegalPersonOfListing::setCpfOrCnpj)
			.addMapping(PersonEntity::getInscricEstad, DtoClass_LegalPersonOfListing::setInscricEstad)
			.addMapping(PersonEntity::getInscricMunicip, DtoClass_LegalPersonOfListing::setInscricMunicip)
			.addMapping(PersonEntity::getEmail, DtoClass_LegalPersonOfListing::setEmail)
			.addMapping(PersonEntity::getSite, DtoClass_LegalPersonOfListing::setSite)
			.addMapping(PersonEntity::getStreet, DtoClass_LegalPersonOfListing::setStreet)
			.addMapping(PersonEntity::getNumber, DtoClass_LegalPersonOfListing::setNumber)
			.addMapping(PersonEntity::getNeighborhood, DtoClass_LegalPersonOfListing::setNeighborhood)
			.addMapping(PersonEntity::getComplement, DtoClass_LegalPersonOfListing::setComplement)
			.addMapping(PersonEntity::getPostalCode, DtoClass_LegalPersonOfListing::setPostalCode)
			.addMapping(PersonEntity::getCityAndStateOrProvince, DtoClass_LegalPersonOfListing::setCityAndStateOrProvince);
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
