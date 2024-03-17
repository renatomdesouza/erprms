package br.com.erprms.domainModel.personDomain.personComponent.personEnum;

public enum StatusPersonalUseEnum {
    USED("Past to be used."),
    NOT_USED("Never used in any personal qualification."),
    DELETED("Registered logical deletion");
    
	private StatusPersonalUseEnum(String s) {}
}
