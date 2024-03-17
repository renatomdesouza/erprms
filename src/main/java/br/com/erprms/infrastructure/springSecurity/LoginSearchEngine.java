package br.com.erprms.infrastructure.springSecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erprms.repositoryAdapter.LoginRepository;

@Service
public class LoginSearchEngine implements UserDetailsService{
    private final LoginRepository loginRepository;
    
	private LoginSearchEngine(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return loginRepository.findByLogin(username);
	}
}
