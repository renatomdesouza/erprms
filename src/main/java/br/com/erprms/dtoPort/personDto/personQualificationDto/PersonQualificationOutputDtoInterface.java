package br.com.erprms.dtoPort.personDto.personQualificationDto;

public interface PersonQualificationOutputDtoInterface {
	void setSpecifiedQualification(String specifiedQualification);
	String getSpecifiedQualification();

	void setPersonName(String personName);
	String getPersonName();

//	void setCpfOrCnpj(String cpfOrCnpj);
//	String getCpfOrCnpj();

	void setObservation(String observation);
	String getObservation();
}
