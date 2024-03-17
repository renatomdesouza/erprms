package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import lombok.Getter;

@Getter
public class DtoClass_NaturalPersonOfRegistry {
	final private String fullNameOrEntityName;
	final private String nickname;
	final private String cpf;
	final private String email;
	final private String site;
	final private String dateBorn;
	final private String maritalStatus;
	final private String cityBorn;
	final private String countryBorn;
	final private SexEnum sex;
	final private String street;
	final private String number;
	final private String neighborhood;
	final private String complement;
	final private String postalCode;
	final private String cityAndStateOrProvince;

	private StatusPersonalUseEnum statusPersonEnum;
	private Boolean isNaturalPerson;
	
	public DtoClass_NaturalPersonOfRegistry (DtoRecord_NaturalPersonOfRegistry naturalPerson) {
		this.fullNameOrEntityName = naturalPerson.fullNameOrEntityName();
		this.nickname = naturalPerson.nickname();
		this.cpf = naturalPerson.cpf();
		this.email = naturalPerson.email();
		this.site = naturalPerson.site();
		this.dateBorn = naturalPerson.dateBorn();
		this.maritalStatus = naturalPerson.maritalStatus();
		this.cityBorn = naturalPerson.cityBorn();
		this.countryBorn = naturalPerson.countryBorn();
		this.sex = naturalPerson.sex();
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
