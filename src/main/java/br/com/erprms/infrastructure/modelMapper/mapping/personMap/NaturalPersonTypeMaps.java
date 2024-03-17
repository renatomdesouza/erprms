package br.com.erprms.infrastructure.modelMapper.mapping.personMap;
 
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClassToNaturalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoClass_NaturalPersonOfUpdate;

@Configuration
public class NaturalPersonTypeMaps {
	private ModelMapper mapper; 
	
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
		mapper.createTypeMap(PersonEntity.class, DtoClassToNaturalPersonOfListing.class)
			.addMapping(PersonEntity::getId, DtoClassToNaturalPersonOfListing::setId)
			.addMapping(PersonEntity::getFullNameOrEntityName, DtoClassToNaturalPersonOfListing::setFullNameOrEntityName)
			.addMapping(PersonEntity::getNickname, DtoClassToNaturalPersonOfListing::setNickname)
			.addMapping(PersonEntity::getStatusPersonEnum, DtoClassToNaturalPersonOfListing::setStatusPersonEnum)
			.addMapping(PersonEntity::getCpfOrCnpj, DtoClassToNaturalPersonOfListing::setCpfOrCnpj)
			.addMapping(PersonEntity::getEmail, DtoClassToNaturalPersonOfListing::setEmail)
			.addMapping(PersonEntity::getSite, DtoClassToNaturalPersonOfListing::setSite)
			.addMapping(PersonEntity::getDateBorn, DtoClassToNaturalPersonOfListing::setDateBorn)
			.addMapping(PersonEntity::getMaritalStatus, DtoClassToNaturalPersonOfListing::setMaritalStatus)
			.addMapping(PersonEntity::getCityBorn, DtoClassToNaturalPersonOfListing::setCityBorn)
			.addMapping(PersonEntity::getCountryBorn, DtoClassToNaturalPersonOfListing::setCountryBorn)
			.addMapping(PersonEntity::getSex, DtoClassToNaturalPersonOfListing::setSex)
			.addMapping(PersonEntity::getStreet, DtoClassToNaturalPersonOfListing::setStreet)
			.addMapping(PersonEntity::getNumber, DtoClassToNaturalPersonOfListing::setNumber)
			.addMapping(PersonEntity::getNeighborhood, DtoClassToNaturalPersonOfListing::setNeighborhood)
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
			.addMapping(DtoClass_NaturalPersonOfUpdate::getCpf, PersonEntity::setCpfOrCnpj)
			.addMapping(DtoClass_NaturalPersonOfUpdate::getEmail, PersonEntity::setEmail)
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
