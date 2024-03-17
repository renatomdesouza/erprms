package br.com.erprms.infrastructure.modelMapper.mapping.personMap;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountantEntityTypeMaps {
	private ModelMapper mapper; 
	
	public AccountantEntityTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Bean
	public void callAccountantEntityMaps() {
		accountantRegistryClassDto_To_AccountantEntity(mapper);
	}
	
	public void accountantRegistryClassDto_To_AccountantEntity(ModelMapper modelMapper) {   
//		modelMapper.createTypeMap(AccountantRegistryClassDto.class, AccountantEntity.class)
//			.addMapping(AccountantRegistryClassDto::getPessFisic, AccountantEntity::setPessFisic)
//			.addMapping(AccountantRegistryClassDto::getPessJuridic, AccountantEntity::setPessJuridic)
//			.addMapping(AccountantRegistryClassDto::getSpecialRegistry, AccountantEntity::setSpecialRegistry)
//			.addMapping(AccountantRegistryClassDto::getInitialDate, AccountantEntity::setInitialDate)
//			.addMapping(AccountantRegistryClassDto::getStatusPessEnum, AccountantEntity::setStatusPessEnum);
	}
}

