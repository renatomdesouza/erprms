package br.com.erprms.domainModel.personDomain.personComponent.personEnum;

public enum StatusPersonalUsedEnum {
    USED("Past to be used."),
    NOT_USED("Never used in any personal qualification."),
    DELETED("Registered logical deletion");
    
	private StatusPersonalUsedEnum(String s) {}
}
