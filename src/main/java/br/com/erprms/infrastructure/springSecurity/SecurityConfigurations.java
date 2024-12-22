package br.com.erprms.infrastructure.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    private final SecurityFilter securityFilter;
	
	public SecurityConfigurations (SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
    
//    public CompromisedPasswordChecker compromisedPasswordChecker() {
//    	
//    }
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//    	UserDetails user = User.withUsername("user")
//    			.password("$2y$10$OKiBDy3jVewHF2ZU8Y0FeeRbP0kBtckX0.zVLbREbnE6X4XC9wEVu")
//    			.authorities("read")
//    			.build();
//    	
////    	UserDetails admin = User.withUsername("admin")
////    			.password("456")
////    			.authorities("admin")
////    			.build();
//    	
//    	return new InMemoryUserDetailsManager(user /*, admin*/);
//    }
    
    
}
