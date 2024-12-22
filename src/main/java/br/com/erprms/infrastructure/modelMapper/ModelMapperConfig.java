package br.com.erprms.infrastructure.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.infrastructure.modelMapper.mapping.AccountantMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.ClientMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.FullTimeEmployeeAndManageMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.LegalPersonMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.ManagerMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.NaturalPersonMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.PartTimeEmployeeMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.Provider_Mapper;
import br.com.erprms.infrastructure.modelMapper.mapping.QualificationSupereclassMapper;
import br.com.erprms.infrastructure.modelMapper.mapping.ResponsibleForLegalPersonMapper;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    ModelMapper obtainModelMapper() {
    	ModelMapper mapper = new ModelMapper();
    	mapper.getConfiguration().setSkipNullEnabled(true);
    	mapper.getConfiguration().setImplicitMappingEnabled(false);

    	new NaturalPersonMapper(mapper);
    	new LegalPersonMapper(mapper);
    	
    	new AccountantMapper(mapper);
		new ClientMapper(mapper);
		new FullTimeEmployeeAndManageMapper(mapper);
		new ManagerMapper(mapper);
		new PartTimeEmployeeMapper(mapper);
		new QualificationSupereclassMapper(mapper);
		new Provider_Mapper(mapper);
		new ResponsibleForLegalPersonMapper(mapper);
		
    	return mapper;
    }	
}
