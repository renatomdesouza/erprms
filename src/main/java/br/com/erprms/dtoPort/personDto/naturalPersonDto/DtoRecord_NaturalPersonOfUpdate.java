package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record DtoRecord_NaturalPersonOfUpdate (
		Long id,

		String fullNameOrEntityName,

		String nickname,

		@Pattern(regexp = "^\\d{11}$")
		String cpf,

		@Email
		String email,

		String site,

		String dateBorn,

		String maritalStatus,

		String cityBorn,

		String countryBorn,

		SexEnum sex,

		String street,

		String number,

		String neighborhood,

		String complement,

		String postalCode,

		String cityStat
	) {}
