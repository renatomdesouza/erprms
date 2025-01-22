package br.com.erprms.infrastructure.springSecurity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfigurations {
    private final SecurityFilter securityFilter;
	
	public SecurityConfigurations (SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        		.csrf(csrf -> csrf.disable())
        		.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        		.authorizeHttpRequests(authorize -> authorize  
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                    .anyRequest().authenticated())
        		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        		.build();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @ConditionalOnProperty(	prefix = "compromisedPasswordChecker", 
				    		name = "isEnabled", 
				    		havingValue = "FALSE")
    CompromisedPasswordChecker compromisedPasswordChecker_Disabled() {
    	log.info("The CompromisedPasswordChecker is disabled");

        return password -> new CompromisedPasswordDecision(false);
    }
    
    @Bean 
    @ConditionalOnProperty(	prefix = "compromisedPasswordChecker", 
				    		name = "isEnabled", 
				    		havingValue = "TRUE")
    CompromisedPasswordChecker compromisedPasswordChecker_Enabled() {
    	log.info("The CompromisedPasswordChecker is enabled");

        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
