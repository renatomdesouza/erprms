package br.com.erprms.domainModel.capitalDomain.passive.passiveType;

public enum ReserveTypeEnum {
	EMPLOYEE_SALARY("reserve for employees payment"),
	INSTALLMENT_PAYABLE("reservation for payment of installments"),
	CASH_PAYMENT("reservation for cash payments"),
	CREDIT_CARD("reserve for credit card payments");

	ReserveTypeEnum(String string){}
}
