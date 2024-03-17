package br.com.erprms.domainModel.personDomain.personComponent;

public interface AddressInterface {
	String getStreet();
	void setStreet(String string);
	
	String getNumber();
	void setNumber(String string);
	
	String getNeighborhood();
	void setNeighborhood(String string);
	
    String getComplement();
    void setComplement(String string);
    
    String getPostalCode();
    void setPostalCode(String string);
    
    String getCityAndStateOrProvince();
    void setCityAndStateOrProvince(String string);
}
