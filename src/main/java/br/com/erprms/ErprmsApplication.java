package br.com.erprms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ErprmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErprmsApplication.class, args);
	}

}
