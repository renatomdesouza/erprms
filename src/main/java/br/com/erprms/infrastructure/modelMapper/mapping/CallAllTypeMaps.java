package br.com.erprms.infrastructure.modelMapper.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.erprms.infrastructure.modelMapper.mapping.personMap.AccountantEntityTypeMaps;
import br.com.erprms.infrastructure.modelMapper.mapping.personMap.FullTimeEmployeeTypeMaps;
import br.com.erprms.infrastructure.modelMapper.mapping.personMap.LegalPersonTypeMaps;
import br.com.erprms.infrastructure.modelMapper.mapping.personMap.ManagerTypeMaps;
import br.com.erprms.infrastructure.modelMapper.mapping.personMap.NaturalPersonTypeMaps;
import br.com.erprms.infrastructure.modelMapper.mapping.personMap.PartTimeEmployeeTypeMaps;

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
		new AccountantEntityTypeMaps(mapper);
		new ManagerTypeMaps(mapper);
		new NaturalPersonTypeMaps(mapper);
		new FullTimeEmployeeTypeMaps(mapper);
		new PartTimeEmployeeTypeMaps(mapper);
		new AccountantEntityTypeMaps(mapper);
		
	}
	
}
