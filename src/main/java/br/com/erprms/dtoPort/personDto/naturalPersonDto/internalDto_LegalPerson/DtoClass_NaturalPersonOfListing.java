package br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import lombok.Setter;

@Setter
public class DtoClass_NaturalPersonOfListing implements PersonListingDto {
	public Long id;
	public String fullNameOrEntityName;
	public String nickname;
	public StatusPersonalUsedEnum statusPersonEnum;
	public String CpfOrCnpj;
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

