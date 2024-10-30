package br.com.erprms.testBuilders;

import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoRecord_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.InputDtoRecord_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client.InputDtoClass_Client;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoRecord_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.InputDtoRecord_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee.InputDtoClass_PartTimeEmployee;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.InputDtoRecord_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider.InputDtoClass_Provider;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.InputDtoRecord_ResponsibleForLegalPerson;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.InputDtoClass_ResponsibleForLegalPerson;

public class Constants_DtosQualifications {
	private static InputDtoRecord_FullTimeEmployeeAndManager FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_RECORD =
			new InputDtoRecord_FullTimeEmployeeAndManager (
					"1",
					"1000",
					"ADMINISTRATION",
					"auxiliar administrativo",
					"123456");

	public static InputDtoClass_FullTimeEmployeeAndManager FULL_TIME_EMPLOYEE_AND_MANAGER_DTO =
            new InputDtoClass_FullTimeEmployeeAndManager (FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_RECORD);


	private static InputDtoRecord_PartTimeEmployee PART_TIME_EMPLOYEE_DTO_RECORD =
			new InputDtoRecord_PartTimeEmployee (
					"1",
					"10",
					"ADMINISTRATION",
					"auxiliar administrativo",
					"123456");

	public static InputDtoClass_PartTimeEmployee PART_TIME_EMPLOYEE_DTO =
			new InputDtoClass_PartTimeEmployee (PART_TIME_EMPLOYEE_DTO_RECORD);


	private static InputDtoRecord_Accountant ACCOUNTANT_DTO_RECORD =
			new InputDtoRecord_Accountant (
					"1",
					"100",
					"Profissional contratado",
					"123456");

	public static InputDtoClass_Accountant ACCOUNTANT_DTO =
			new InputDtoClass_Accountant (ACCOUNTANT_DTO_RECORD);


	private static InputDtoRecord_Client CLIENT_DTO_RECORD =
			new InputDtoRecord_Client (
					"1",
					"Cliente fidelizado",
					null,
					"360");

	public static InputDtoClass_Client CLIENT_DTO =
			new InputDtoClass_Client (CLIENT_DTO_RECORD);


	private static InputDtoRecord_Provider PROVIDER_DTO_RECORD =
			new InputDtoRecord_Provider (
					"1",
					"Cliente fidelizado",
					null);

	public static InputDtoClass_Provider PROVIDER_DTO =
			new InputDtoClass_Provider(PROVIDER_DTO_RECORD);


	private static InputDtoRecord_ResponsibleForLegalPerson RESPONSIBLE_FOR_LEGAL_PERSON_DTO_RECORD =
			new InputDtoRecord_ResponsibleForLegalPerson (
					"1",
					"Cliente fidelizado",
					null);

	public static InputDtoClass_ResponsibleForLegalPerson RESPONSIBLE_FOR_LEGAL_PERSON_DTO =
			new InputDtoClass_ResponsibleForLegalPerson(RESPONSIBLE_FOR_LEGAL_PERSON_DTO_RECORD);

}



