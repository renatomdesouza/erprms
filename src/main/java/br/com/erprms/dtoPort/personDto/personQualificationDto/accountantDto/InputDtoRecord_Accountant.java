package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InputDtoRecord_Accountant(
        @NotBlank(message = "The \"Id\" of the accountant cannot be null")
        @Pattern(	regexp = "^[\\d]+$",
                    message = "The \"Id\" must be described with only decimal digits")
        @Size(min = 1, max = 7, message = "The field must have 1 to 7 characters")
        String person_Id,

        @NotBlank(message = "The \"monthlyCost\" of the accountant cannot be null")
        @Pattern(	regexp = "^[0-9.]+$",
                    message = "The \"Id\" must be described with digit and dot format, example: = 1200.50")
        @Size(min = 1, max = 7, message = "The field must have 1 to 7 characters")
        String monthlyCost,

        @Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ.,\\s]+$",
                    message = "The full name must be described with letters only")
        @Size(min = 5, max = 200, message = "The field must have 5 to 200 characters")
        String observation,

        @NotBlank(message = "The \"professionalRegistry\" of the accountant cannot be null")
        @Pattern(	regexp = "^[A-Za0-9záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
                    message = "The \"professionalRegistry\" must be described with letters only")
        @Size(min = 3, max = 20, message = "The field must have 3 to 50 characters")
        String professionalRegistry
) {}
