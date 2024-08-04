package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class DtoClass_NaturalPersonOfRegistry {
	private String fullNameOrEntityName;
	private String nickname;
	private Long cpf;
	private String email;
	private String site;
	private String dateBorn;
	private String maritalStatus;
	private String cityBorn;
	private String countryBorn;
	private SexEnum sex;
	private String street;
	private String number;
	private String neighborhood;
	private String complement;
	private String postalCode;
	private String cityAndStateOrProvince;

	StatusPersonalUseEnum statusPersonEnum;

	Boolean isNaturalPerson;
	
	public DtoClass_NaturalPersonOfRegistry (DtoRecord_NaturalPersonOfRegistry naturalPerson) {
		this.fullNameOrEntityName = naturalPerson.fullNameOrEntityName();
		this.nickname = naturalPerson.nickname();
		this.cpf =
				Long.parseLong(naturalPerson.cpf());

		this.email = naturalPerson.email();
		this.site = naturalPerson.site();
		this.dateBorn = naturalPerson.dateBorn();
		this.maritalStatus = naturalPerson.maritalStatus();
		this.cityBorn = naturalPerson.cityBorn();
		this.countryBorn = naturalPerson.countryBorn();
		this.sex =
				SexEnum.valueOf(naturalPerson.sex());

		this.street = naturalPerson.street();
		this.number = naturalPerson.number();
		this.neighborhood = naturalPerson.neighborhood();
		this.complement = naturalPerson.complement();
		this.postalCode = naturalPerson.postalCode();
		this.cityAndStateOrProvince = naturalPerson.cityAndStateOrProvince();
		
		this.statusPersonEnum = StatusPersonalUseEnum.NOT_USED;
		this.isNaturalPerson = true;

	}
}
