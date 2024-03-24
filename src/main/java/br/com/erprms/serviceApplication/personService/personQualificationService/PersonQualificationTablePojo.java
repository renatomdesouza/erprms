package br.com.erprms.serviceApplication.personService.personQualificationService;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonQualificationTablePojo {
	private String specified_qualification;
	private Long id;
	private LocalDate final_date;
	private LocalDate initial_date;
	private String observation;
	private String professional_registry;
	private BigDecimal hourly_rate;
	private Long person_id;
	private String sector;
	private BigDecimal monthly_salary;
}
