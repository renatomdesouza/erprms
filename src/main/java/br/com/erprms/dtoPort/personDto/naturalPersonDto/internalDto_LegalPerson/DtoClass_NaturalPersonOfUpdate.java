package br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.dtoPort.personDto.PersonRegistryAndUpdateDto;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DtoClass_NaturalPersonOfUpdate implements PersonRegistryAndUpdateDto{
		final private Long id;
		final private String fullNameOrEntityName;
		final private String nickname;
		final private Long cpfOrCnpj;
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

	    public DtoClass_NaturalPersonOfUpdate(DtoRecord_NaturalPersonOfUpdate naturalPersonUpdate) {
	    	this.id	= 
					Long.parseLong(naturalPersonUpdate.id());

	    	this.fullNameOrEntityName = naturalPersonUpdate.fullNameOrEntityName();
	    	this.nickname = naturalPersonUpdate. nickname();
	    	this.cpfOrCnpj = 
					naturalPersonUpdate.cpf() == null ? null : Long.parseLong(naturalPersonUpdate.cpf());

			this.email = naturalPersonUpdate.email();
	    	this.site = naturalPersonUpdate.site();
	    	this.dateBorn = naturalPersonUpdate.dateBorn();
	    	this.maritalStatus = naturalPersonUpdate.maritalStatus();
	    	this.cityBorn = naturalPersonUpdate.cityBorn();
	    	this.countryBorn = naturalPersonUpdate.countryBorn();
	    	this.sex =
					naturalPersonUpdate.sex() == null ? null : SexEnum.valueOf(naturalPersonUpdate.sex());

	    	this.street = naturalPersonUpdate.street();
	    	this.number = naturalPersonUpdate.number();
	    	this.neighborhood = naturalPersonUpdate.neighborhood();
	    	this.complement = naturalPersonUpdate.complement();
	    	this.postalCode = naturalPersonUpdate.postalCode();
	    	this.cityAndStateOrProvince = naturalPersonUpdate.cityAndStateOrProvince();
	    }
	 }
