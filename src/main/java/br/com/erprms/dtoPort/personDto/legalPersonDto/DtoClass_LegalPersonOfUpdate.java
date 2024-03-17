package br.com.erprms.dtoPort.personDto.legalPersonDto;

import lombok.Getter;

@Getter
public class DtoClass_LegalPersonOfUpdate {
	final private Long id;
	final private String fullNameOrEntityName;
	final private String nickname;
	final private String cnpj;
	final private String email;
	final private String site;
	final	private	String	inscricEstad;
	final	private	String	inscricMunicip;
	final private String street;
	final private String number;
	final private String neighborhood;
	final private String complement;
	final private String postalCode;
	final private String cityStat;

    public DtoClass_LegalPersonOfUpdate(DtoRecord_LegalPersonOfUpdate legalPerson) {
    	this.id	= legalPerson.id();
    	this.fullNameOrEntityName = legalPerson.fullNameOrEntityName();
    	this.nickname = legalPerson. nickname();
    	this.cnpj = legalPerson. cnpj();
    	this.email = legalPerson.email();
    	this.site = legalPerson.site();
    	this.inscricEstad = legalPerson.inscricEstad();
		this.inscricMunicip = legalPerson.inscricMunicip();
    	this.street = legalPerson.street();
    	this.number = legalPerson.number();
    	this.neighborhood = legalPerson.neighborhood();
    	this.complement = legalPerson.complement();
    	this.postalCode = legalPerson.postalCode();
    	this.cityStat = legalPerson.cityStat();
    }
}
