package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import jakarta.validation.constraints.*;

public record DtoRecord_NaturalPersonOfRegistry (
		@NotNull(message = "The full name of the natural person cannot be null")
		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The full name must be described with letters only")
		@Size(min = 5, max = 100, message = "The field must have 5 to 100 characters")
		String fullNameOrEntityName,

		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The nickname must be described with letters only")
		@Size(min = 5, max = 20, message = "The field must have 5 to 20 characters")
		String nickname,

		@NotBlank(message = "The CPF of the natural person cannot be null")
		@Pattern(	regexp = "^\\d{11}$",
					message = "The CPF must be described with only 11 decimal digits")
		String cpf,

		@Email
		@NotBlank(message = "The email of the natural person cannot be null")
		@Size(min = 10, max = 100, message = "The field must have 10 to 100 characters")
		String email,

		@Pattern(	regexp = "^((http|https|ftp):\\/\\/)?([\\- \\w]+\\.)+\\w{2,3}(\\/ [%\\-\\w]+(\\.\\w[2,])?)*$",
					message = "The website must be described with the correct name")
		@Size(min = 5, max = 200, message = "The field must have 5 to 200 characters")
		String site,

		@NotBlank(message = "The date born of the natural person cannot be null")
		@Pattern(	regexp = "^(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[012])\\/\\d{4}$",  // "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$"
					message = "The date must be described with the correct format")
		String dateBorn,

		@NotBlank(message = "The marital status of the natural person cannot be null")
		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+$",
					message = "The marital status must be described with letters only")
		@Size(min = 2, max = 20, message = "The field must have 2 to 20 characters")
		String maritalStatus,

		@NotBlank(message = "The city born of the natural person cannot be null")
		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The city born must be described with letters only")
		@Size(min = 5, max = 100, message = "The field must have 5 to 100 characters")
		String cityBorn,

		@NotBlank(message = "The country born of the natural person cannot be null")
		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The country born must be described with letters only")
		@Size(min = 5, max = 50, message = "The field must have 5 to 50 characters")
		String countryBorn,

		@NotBlank(message = "The sex of the natural person cannot be null")
		@Pattern(	regexp = "^(MASCULINE|FEMININE|NOT_DECLARED)$",
					message = "Available patterns for filling: MASCULINE,FEMININE or NOT_DECLARED ")
		String sex,

		@NotBlank(message = "The local street of the natural person cannot be null")
		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The street must be described with letters only")
		@Size(min = 5, max = 100, message = "The field must have 5 to 100 characters")
		String street,

		@NotBlank(message = "The local number of the person cannot be null")
		@Pattern(	regexp = "^[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
				message = "The \"number\" must be described with numbers and letters only")
		@Size(min = 1, max = 10, message = "The field must have 1 to 10 characters")
		String number,

		@NotBlank(message = "The local neighborhood of the person cannot be null")
		@Pattern(	regexp = "^[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The neighborhood must be described with numbers and letters only")
		@Size(min = 5, max = 100, message = "The field must have 5 to 100 characters")
		String neighborhood,

		@Pattern(	regexp = "^[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$",
					message = "The complement must be described with numbers and letters only")
		@Size(min = 5, max = 100, message = "The field must have 5 to 100 characters")
		String complement,

		@NotBlank(message = "The postal code of the person cannot be null")
		@Pattern(	regexp = "^\\d{5}-\\d{3}$",
					message = "The CEP must be described with the correct format")
		String postalCode,

		@NotBlank(message = "The city and state or province of the person cannot be null")
		@Pattern(	regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s\\/-]+$",
					message = "The city and state or province must be described with letters only")
		@Size(min = 5, max = 100, message = "The field must have 5 to 100 characters")
		String cityAndStateOrProvince
	) {
}
