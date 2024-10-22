package br.com.erprms.dtoPort.personDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;

public interface PersonListingDto {
	void setStatusPersonEnum(StatusPersonalUsedEnum statusPersonalUseEnum);
	
 	void setFullNameOrEntityName(String string);
 	
	void setNickname(String string);
	
 	void setCpfOrCnpj(String string);
 	
 	void setEmail(String string);
 	
 	void setSite(String string);
}
