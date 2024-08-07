package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto;

import java.math.BigDecimal;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InputDtoRecord_FullTimeEmployeeAndManager(
		@NotBlank(message = "The \"Id\" of the manager or full time employee cannot be null")
		@Pattern(	regexp = "^[\\d]+$",
				message = "The \"Id\" must be described with only decimal digits")
		@Size(min = 1, max = 7, message = "The field must have 1 to 7 characters")
		String person_Id,

		@NotBlank(message = "The \"monthlyCost\" of the manager or full time employee cannot be null")
		@Pattern(	regexp = "^[0-9.]+$",
				message = "The \"Id\" must be described with digit and dot format, example: = 1200.50")
		@Size(min = 1, max = 7, message = "The field must have 1 to 7 characters")
		String monthlySalary,

		@Pattern(	regexp = "^(ADMINISTRATION|SALES_SECTOR|PURCHASING_SECTOR|WAREHOUSE)$",
				message = "The full name must be described with letters only")
		@Size(min = 5, max = 200, message = "The field must have 5 to 200 characters")
		String sector,

		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ.,\\s]+$",
				message = "The full name must be described with letters only")
		@Size(min = 5, max = 200, message = "The field must have 5 to 200 characters")
		String observation,

		@Pattern(	regexp = "^[A-Za0-9záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
				message = "The \"professionalRegistry\" must be described with letters only")
		@Size(min = 3, max = 20, message = "The field must have 3 to 50 characters")
		String professionalRegistry
		) {}
