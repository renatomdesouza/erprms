package br.com.erprms.dtoPort.personDto.legalPersonDto;

public record DtoRecord_LegalPersonOfRegistry(
		String fullNameOrEntityName,
		String nickname,
	    String cnpj,
	    String email,
	    String site,
	    String inscricEstad,
	    String inscricMunicip,
	    String street,
	    String number,
		String neighborhood,
		String complement,
	    String postalCode,
	    String cityAndStateOrProvince
		) {
}
