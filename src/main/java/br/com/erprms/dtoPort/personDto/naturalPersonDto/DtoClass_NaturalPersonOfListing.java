package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import lombok.Setter;

@Setter
public class DtoClass_NaturalPersonOfListing implements PersonListingDto {
	public Long id;
	public String fullNameOrEntityName;
	public String nickname;
	public StatusPersonalUseEnum statusPersonEnum;
	public Long CpfOrCnpj;
	public String email;
	public String site;
	public String dateBorn;
	public String maritalStatus;
	public String cityBorn;
	public String countryBorn;
	public SexEnum sex;
	public String street;
	public String number;
	public String neighborhood;
	public String complement;
	public String postalCode;
	public String cityAndStateOrProvince;
}

