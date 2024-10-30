package br.com.erprms.testBuilders;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;

public class Constants_QualificationClass {

	public static Class<PersonQualificationInputDtoInterface> manager() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var managerPersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity" 
				+ ".employeePersonQualificator.ManagerPersonQualification");
		return managerPersonQualification_class;
	}
	
	public static Class<PersonQualificationInputDtoInterface> fullTimeEmployee() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var fullTimeEmployeePersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity" 
				+ ".employeePersonQualificator.FullTimeEmployeePersonQualification");
		return fullTimeEmployeePersonQualification_class;
	}

	public static Class<PersonQualificationInputDtoInterface> partTimeEmployee() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var partTimeEmployeePersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity" 
				+ ".employeePersonQualificator.PartTimeEmployeePersonQualification");
		return partTimeEmployeePersonQualification_class;
	}

	public static Class<PersonQualificationInputDtoInterface> accountant() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var accountantPersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity"
				+ ".generatePersonQualificatorInheritor.AccountantPersonQualification");
		return accountantPersonQualification_class;
	}

	public static Class<PersonQualificationInputDtoInterface> client() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var clientPersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity"
				+ ".generatePersonQualificatorInheritor.ClientPersonQualification");
		return clientPersonQualification_class;
	}

	public static Class<PersonQualificationInputDtoInterface> provider() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var providerPersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity" 
				+ ".generatePersonQualificatorInheritor.ProviderPersonQualification");
		return providerPersonQualification_class;
	}
	
	public static Class<PersonQualificationInputDtoInterface> responsibleForLegalPerson() throws ClassNotFoundException{
		@SuppressWarnings("unchecked")
		var responsibleForLegalPersonQualification_class =  
		(Class<PersonQualificationInputDtoInterface>) Class.forName(
				"br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity"
				+ ".generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification");
		return responsibleForLegalPersonQualification_class;
	}
}

