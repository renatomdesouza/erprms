package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InputDtoRecord_ResponsibleForLegalPerson(
        @NotBlank(message = "The \"Id\" of the accountant cannot be null")
        @Pattern(	regexp = "^[\\d]+$",
                message = "The \"Id\" must be described with only decimal digits")
        @Size(min = 1, max = 7, message = "The field must have 1 to 7 characters")
        String person_Id,

        @Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ.,\\s]+$",
                message = "The full name must be described with letters only")
        @Size(min = 5, max = 200, message = "The field must have 5 to 200 characters")
        String observation,

        @Pattern(	regexp = "^[A-Za0-9záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
                message = "The \"professionalRegistry\" must be described with letters only")
        @Size(min = 3, max = 20, message = "The field must have 3 to 50 characters")
        String professionalRegistry
) {}
