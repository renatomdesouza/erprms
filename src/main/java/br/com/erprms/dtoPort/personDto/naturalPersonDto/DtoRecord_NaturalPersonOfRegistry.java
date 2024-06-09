package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;

public record DtoRecord_NaturalPersonOfRegistry(
		@NonNull
		String fullNameOrEntityName,

		String nickname,

//		@Pattern(regexp = "^\\d{11}$")
		Long cpf,

		@Email @NonNull
		String email,

		String site,

		@NonNull
		String dateBorn,

		@NonNull
		String maritalStatus,

		@NonNull
		String cityBorn,

		@NonNull
		String countryBorn,

		@NonNull
		SexEnum sex,

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
