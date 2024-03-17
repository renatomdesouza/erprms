package br.com.erprms.repositoryAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.erprms.domainModel.LoginUser;

public interface LoginRepository extends JpaRepository<LoginUser, Long> {
	UserDetails findByLogin(String username);
}
