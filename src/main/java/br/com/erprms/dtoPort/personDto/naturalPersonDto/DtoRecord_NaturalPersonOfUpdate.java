package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;

public record DtoRecord_NaturalPersonOfUpdate (
		Long id,
		String fullNameOrEntityName,
	    String nickname,
	    String cpf,
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
