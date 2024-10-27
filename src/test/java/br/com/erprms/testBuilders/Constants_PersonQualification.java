package br.com.erprms.testBuilders;

import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoRecord_FullTimeEmployeeAndManager;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager.InputDtoClass_FullTimeEmployeeAndManager;

import java.time.LocalDateTime;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
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

    
    public static PersonQualificationSuperclassEntity PERSON_QUALIFICATION_SUPERCLASS =
    		new PersonQualificationSuperclassEntity(
					1L,
					"User logged",
					true,
					null,
					HttpVerbEnum.POST,
					"Any registry",
                    LocalDateTime.of(2024, 2, 3, 16, 30),
                    null,
                    "example registry",
                    NATURAL_PERSON);
    
    public static PersonQualificationSuperclassEntity PERSON_QUALIFICATION_SUPERCLASS_02 =
    		new PersonQualificationSuperclassEntity(
					1L,
					"User logged",
					true,
					null,
					HttpVerbEnum.POST,
					"Any registry",
                    LocalDateTime.of(2024, 2, 3, 16, 30),
                    null,
                    "example registry",
                    NATURAL_PERSON);
    
    
    

}
