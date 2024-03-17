package br.com.erprms.domainModel.personDomain.personComponent;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;

public interface PepleCommonDataInterface {
	StatusPersonalUseEnum getStatusPersonEnum();
	void setStatusPersonEnum(StatusPersonalUseEnum statusPersonalUseEnum);

	Boolean getIsNaturalPerson();
	void setIsNaturalPerson(Boolean bollean);
	
 	String getFullNameOrEntityName();
 	void setFullNameOrEntityName(String string);
 	
	String getNickname();
	void setNickname(String string);
	
 	String getCpfOrCnpj();
 	void setCpfOrCnpj(String string);
 	
 	String getEmail();
 	void setEmail(String string);
 	
 	String getSite();
 	void setSite(String string);
}
