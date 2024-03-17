package br.com.erprms.domainModel.productDomain.classificationAndUnit;

public enum ProductClassEnum {
    PERISHABLE_WITH_EXPIRATION_DATE("perishable with expiration date - for exemple: manufactures"),
    PERISHABLE_WITHOUT_EXPIRATION_DATE("perishable without expiration date - for exemple: fresh fruits and vegetables"),
    DURABLE_GOOD("durble good - for exemple: manufactured");

    ProductClassEnum(String s){}
}
