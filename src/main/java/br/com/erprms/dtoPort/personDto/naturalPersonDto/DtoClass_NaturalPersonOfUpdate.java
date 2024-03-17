package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import lombok.Getter;

@Getter
public class DtoClass_NaturalPersonOfUpdate implements PersonListingDto {
		final private Long id;
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

	    public DtoClass_NaturalPersonOfUpdate(DtoRecord_NaturalPersonOfUpdate naturalPersonUpdate) {
	    	this.id	= naturalPersonUpdate.id();
	    	this.fullNameOrEntityName = naturalPersonUpdate.fullNameOrEntityName();
	    	this.nickname = naturalPersonUpdate. nickname();
	    	this.cpf = naturalPersonUpdate. cpf();
	    	this.email = naturalPersonUpdate.email();
	    	this.site = naturalPersonUpdate.site();
	    	this.dateBorn = naturalPersonUpdate.dateBorn();
	    	this.maritalStatus = naturalPersonUpdate.maritalStatus();
	    	this.cityBorn = naturalPersonUpdate.cityBorn();
	    	this.countryBorn = naturalPersonUpdate.countryBorn();
	    	this.sex = naturalPersonUpdate.sex();
	    	this.street = naturalPersonUpdate.street();
	    	this.number = naturalPersonUpdate.number();
	    	this.neighborhood = naturalPersonUpdate.neighborhood();
	    	this.complement = naturalPersonUpdate.complement();
	    	this.postalCode = naturalPersonUpdate.postalCode();
	    	this.cityAndStateOrProvince = naturalPersonUpdate.cityBorn();
	    }

		@Override
		public void setStatusPersonEnum(StatusPersonalUseEnum statusPersonalUseEnum) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setFullNameOrEntityName(String string) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNickname(String string) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setCpfOrCnpj(String string) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setEmail(String string) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setSite(String string) {
			// TODO Auto-generated method stub
			
		}
	 }
