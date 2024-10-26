package br.com.erprms.testBuilders;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoRecord_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;

public class Constants_PersonQualification {

	private static InputDtoRecord_FullTimeEmployeeAndManager FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_RECORD =
			new InputDtoRecord_FullTimeEmployeeAndManager (
					"1",
					"1000",
					"ADMINISTRATION",
					"auxiliar administrativo",
					"123456");
	

    public static InputDtoClass_FullTimeEmployeeAndManager FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_CLASS =
            new InputDtoClass_FullTimeEmployeeAndManager (FULL_TIME_EMPLOYEE_AND_MANAGER_DTO_RECORD);

    

}
