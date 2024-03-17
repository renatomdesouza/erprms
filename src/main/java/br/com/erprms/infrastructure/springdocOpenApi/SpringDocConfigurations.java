package br.com.erprms.infrastructure.springdocOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(
        		new Components()
        				.addSecuritySchemes(
		        				"bearer-key", 
		        				new SecurityScheme()
			        				.type(SecurityScheme.Type.HTTP)
			        				.scheme("bearer")
					                .bearerFormat("JWT")))
				        .info(new Info()
				        		.title("ERP.rms Application Programming Interface")
				        		.description("""
			              			This  is  a  sample of the beginning of the development of an API (Application Programming Interface),  
			              			mainly using Spring Boot technologies with the Java programming language in version 17, and a RESTFull 
			              			Back-End interface.
				        			"""));
    }
}


