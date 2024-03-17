package br.com.erprms.domainModel.personDomain.personComponent;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;

public interface NaturalPersonInterface {
	String getDateBorn();
	void setDateBorn(String string);
	
	String getMaritalStatus();
	void setMaritalStatus(String string);
	
	String getCityBorn();
	void setCityBorn(String string);
	
	String getCountryBorn();
	void setCountryBorn(String string);
	
	SexEnum getSex();
	void setSex(SexEnum sesEnum);
}
