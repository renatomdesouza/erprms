package br.com.erprms.infrastructure.modelMapper.mapping.personMap;
 
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfUpdate;

@Configuration
public class NaturalPersonTypeMaps {
	private final ModelMapper mapper;
	
	public NaturalPersonTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Bean
	public void callTypeMapsOfNaturalPerson() {
		dtoClassToNaturalPersonOfRegistry_To_PersonEntity_TypeMap(mapper);
		personEntity_To_DtoClassToNaturalPersonOfListing_TypeMap(mapper);
		dtoClassToNaturalPersonOfUpdate_To_PersonEntity_TypeMap(mapper);  
	}
	
	public void dtoClassToNaturalPersonOfRegistry_To_PersonEntity_TypeMap(ModelMapper mapper) {
		mapper.createTypeMap(DtoClass_NaturalPersonOfRegistry.class, PersonEntity.class)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getFullNameOrEntityName, PersonEntity::setFullNameOrEntityName)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getNickname, PersonEntity::setNickname)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getCpf, PersonEntity::setCpfOrCnpj)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getEmail, PersonEntity::setEmail)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getSite, PersonEntity::setSite)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getStreet, PersonEntity::setStreet)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getNumber, PersonEntity::setNumber)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getNeighborhood, PersonEntity::setNeighborhood)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getComplement, PersonEntity::setComplement)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getPostalCode, PersonEntity::setPostalCode)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getCityAndStateOrProvince, PersonEntity::setCityAndStateOrProvince)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getDateBorn, PersonEntity::setDateBorn)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getMaritalStatus, PersonEntity::setMaritalStatus)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getCityBorn, PersonEntity::setCityBorn)
			.addMapping(DtoClass_NaturalPersonOfRegistry::getCountryBorn, PersonEntity::setCountryBorn)
			// alternative use with Lambda Expression
			.<SexEnum>addMapping((ori) -> ori.getSex(), (dest, v) -> dest.setSex(v))
			.<StatusPersonalUseEnum>addMapping((ori) -> ori.getStatusPersonEnum(), (dest, v) -> dest.setStatusPersonEnum(v))
			.<Boolean>addMapping((ori) -> ori.getIsNaturalPerson() , (dest, v) -> dest.setIsNaturalPerson(v));
	}
	
	public void personEntity_To_DtoClassToNaturalPersonOfListing_TypeMap(ModelMapper mapper) {   
		mapper.createTypeMap(PersonEntity.class, DtoClass_NaturalPersonOfListing.class)
			.addMapping(PersonEntity::getId, DtoClass_NaturalPersonOfListing::setId)
			.addMapping(PersonEntity::getFullNameOrEntityName, DtoClass_NaturalPersonOfListing::setFullNameOrEntityName)
			.addMapping(PersonEntity::getNickname, DtoClass_NaturalPersonOfListing::setNickname)
			.addMapping(PersonEntity::getStatusPersonEnum, DtoClass_NaturalPersonOfListing::setStatusPersonEnum)
			.addMapping(PersonEntity::getCpfOrCnpj, DtoClass_NaturalPersonOfListing::setCpfOrCnpj)
			.addMapping(PersonEntity::getEmail, DtoClass_NaturalPersonOfListing::setEmail)
			.addMapping(PersonEntity::getSite, DtoClass_NaturalPersonOfListing::setSite)
			.addMapping(PersonEntity::getDateBorn, DtoClass_NaturalPersonOfListing::setDateBorn)
			.addMapping(PersonEntity::getMaritalStatus, DtoClass_NaturalPersonOfListing::setMaritalStatus)
			.addMapping(PersonEntity::getCityBorn, DtoClass_NaturalPersonOfListing::setCityBorn)
			.addMapping(PersonEntity::getCountryBorn, DtoClass_NaturalPersonOfListing::setCountryBorn)
			.addMapping(PersonEntity::getSex, DtoClass_NaturalPersonOfListing::setSex)
			.addMapping(PersonEntity::getStreet, DtoClass_NaturalPersonOfListing::setStreet)
			.addMapping(PersonEntity::getNumber, DtoClass_NaturalPersonOfListing::setNumber)
			.addMapping(PersonEntity::getNeighborhood, DtoClass_NaturalPersonOfListing::setNeighborhood)
			// alternative use with Lambda Expression
			.<String>addMapping((ori) -> ori.getComplement(), (dest, v) -> dest.setComplement(v))
			.<String>addMapping((ori) -> ori.getPostalCode(), (dest, v) -> dest.setPostalCode(v))
			.<String>addMapping((ori) -> ori.getCityAndStateOrProvince(), (dest, v) -> dest.setCityAndStateOrProvince(v));
	}
	
	public void dtoClassToNaturalPersonOfUpdate_To_PersonEntity_TypeMap(ModelMapper mapper) {
		mapper.createTypeMap(DtoClass_NaturalPersonOfUpdate.class, PersonEntity.class)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getId, PersonEntity::setId)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getFullNameOrEntityName, PersonEntity::setFullNameOrEntityName)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getNickname, PersonEntity::setNickname)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getCpfOrCnpj, PersonEntity::setCpfOrCnpj)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getSite, PersonEntity::setSite)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getDateBorn, PersonEntity::setDateBorn)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getMaritalStatus, PersonEntity::setMaritalStatus)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getCityBorn, PersonEntity::setCityBorn)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getCountryBorn, PersonEntity::setCountryBorn)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getSex, PersonEntity::setSex)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getStreet, PersonEntity::setStreet)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getNumber, PersonEntity::setNumber)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getNeighborhood, PersonEntity::setNeighborhood)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getComplement, PersonEntity::setComplement)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getPostalCode, PersonEntity::setPostalCode)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getCityAndStateOrProvince, PersonEntity::setCityAndStateOrProvince);
	}
}
