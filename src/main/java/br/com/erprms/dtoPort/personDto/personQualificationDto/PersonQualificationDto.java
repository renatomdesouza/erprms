package br.com.erprms.dtoPort.personDto.personQualificationDto;

import java.time.LocalDate;

public interface PersonQualificationDto {
	String getSpecifiedQualification();
	
	LocalDate getInitialDate();
}
