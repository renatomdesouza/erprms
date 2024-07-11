package br.com.erprms.dtoPort.personDto.legalPersonDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;

public record DtoRecord_LegalPersonOfRegistry(
		@NonNull
		String fullNameOrEntityName,

		String nickname,

		@NonNull 
//		@Pattern(regexp = "^\\d{14}$")
		Long cnpj,

		@Email
		@NonNull
		String email,

		String site,

		@NonNull
		String inscricEstad,

		String inscricMunicip,

		@NonNull
		String street,

		@NonNull
		String number,

		@NonNull
		String neighborhood,

		String complement,

		@NonNull
		String postalCode,

		@NonNull
		String cityAndStateOrProvince
		) {
}
