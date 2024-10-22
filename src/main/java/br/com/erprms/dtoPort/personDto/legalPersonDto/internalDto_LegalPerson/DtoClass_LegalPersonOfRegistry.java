package br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.PersonRegistryAndUpdateDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import lombok.Getter;

@Getter
public class DtoClass_LegalPersonOfRegistry implements PersonRegistryAndUpdateDto{
	final	private	String	fullNameOrEntityName;
	final	private	String	nickname;
	final	private	Long	cnpj;
	final	private	String	email;
	final	private	String	site;
	final	private	String	inscricEstad;
	final	private	String	inscricMunicip;
	final	private	String	street;
	final	private	String	number;
	final	private	String	neighborhood;
	final	private	String	complement;
	final	private	String	postalCode;
	final	private	String	cityAndStateOrProvince;
	private	StatusPersonalUsedEnum statusPersonEnum;
	private Boolean isNaturalPerson;
	
	public DtoClass_LegalPersonOfRegistry(DtoRecord_LegalPersonOfRegistry legalPerson) {
		this.fullNameOrEntityName = legalPerson.fullNameOrEntityName();
		this.nickname = legalPerson.nickname();
		this.cnpj =
                Long.parseLong(legalPerson.cnpj());

		this.email = legalPerson.email();
		this.site = legalPerson.site();
		this.inscricEstad = legalPerson.inscricEstad();
		this.inscricMunicip = legalPerson.inscricMunicip();
		this.street = legalPerson.street();
		this.number = legalPerson.number();
		this.neighborhood = legalPerson.neighborhood();
		this.complement = legalPerson.complement();
		this.postalCode = legalPerson.postalCode();
		this.cityAndStateOrProvince = legalPerson.cityAndStateOrProvince();
		this.statusPersonEnum = StatusPersonalUsedEnum.NOT_USED;
		this.isNaturalPerson = false;
	}
}
