package br.com.erprms.dtoPort.personDto.legalPersonDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record DtoRecord_LegalPersonOfUpdate(
		Long id,

		String fullNameOrEntityName,

		String nickname,

		@Pattern(regexp = "^\\d{14}$")
		Long cnpj,

		@Email
		String email,

		String site,

		String	inscricEstad,

		String	inscricMunicip,

		String street,

		String number,

		String neighborhood,

		String complement,

		String postalCode,

		String cityStat
		) {
}
