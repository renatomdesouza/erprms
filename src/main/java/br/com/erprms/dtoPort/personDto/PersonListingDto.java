package br.com.erprms.dtoPort.personDto;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;

public interface PersonListingDto {
	void setStatusPersonEnum(StatusPersonalUseEnum statusPersonalUseEnum);
	
 	void setFullNameOrEntityName(String string);
 	
	void setNickname(String string);
	
 	void setCpfOrCnpj(Long string);
 	
 	void setEmail(String string);
 	
 	void setSite(String string);
}
