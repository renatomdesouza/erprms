package br.com.erprms.infrastructure.localDateTime_Setter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LocalDateTime_Setter {
	public LocalDateTime nowSetter() {
		return LocalDateTime.now();
	}
}
