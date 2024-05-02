package br.com.erprms.infrastructure.modelMapper.mapping;

import br.com.erprms.infrastructure.modelMapper.mapping.personMap.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallAllTypeMaps {
	ModelMapper mapper;
	
	public CallAllTypeMaps(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Bean
	public void callBeansOfTypeMaps() {
		new NaturalPersonTypeMaps(mapper);
		new LegalPersonTypeMaps(mapper);
		new AccountantTypeMaps(mapper);
		new ManagerTypeMaps(mapper);
		new NaturalPersonTypeMaps(mapper);
		new FullTimeEmployeeTypeMaps(mapper);
		new PartTimeEmployeeTypeMaps(mapper);
		new AccountantTypeMaps(mapper);
		new ClientTypeMaps(mapper);
	}
	
}
