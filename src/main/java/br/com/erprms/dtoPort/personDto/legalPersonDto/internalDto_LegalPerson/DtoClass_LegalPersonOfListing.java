package br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import lombok.Setter;

@Setter
public class DtoClass_LegalPersonOfListing implements PersonListingDto{
	public Long id;
	public String fullNameOrEntityName;
	public String nickname;
	public String CpfOrCnpj;
	public StatusPersonalUsedEnum statusPersonEnum;
	public String email;
	public String site;
	public	String	inscricEstad;
	public	String	inscricMunicip;
	public String street;
	public String number;
	public String neighborhood;
	public String complement;
	public String postalCode;
	public String cityAndStateOrProvince;
}
