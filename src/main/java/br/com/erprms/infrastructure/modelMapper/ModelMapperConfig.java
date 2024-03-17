package br.com.erprms.infrastructure.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.infrastructure.modelMapper.mapping.CallAllTypeMaps;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper obtainModelMapper() {
    	ModelMapper mapper = new ModelMapper();
    	mapper.getConfiguration().setSkipNullEnabled(true);
    	mapper.getConfiguration().setImplicitMappingEnabled(false);
    	new CallAllTypeMaps(mapper);
        return mapper;
    }
    
}
