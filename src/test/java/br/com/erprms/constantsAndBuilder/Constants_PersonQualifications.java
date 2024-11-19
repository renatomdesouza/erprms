package br.com.erprms.constantsAndBuilder;

import static br.com.erprms.constantsAndBuilder.Constants_Person.NATURAL_PERSON;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.test.context.ActiveProfiles;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.FullTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.PartTimeEmployeePersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.AccountantPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ClientPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ProviderPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.generatePersonQualificatorInheritor.ResponsibleForLegalPersonQualification;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

@ActiveProfiles("test")
public class Constants_PersonQualifications {
    
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
    
    public static ManagerPersonQualification OLD_MANAGER_PERSON_QUALIFICATION =
			new ManagerPersonQualification(
					PERSON_QUALIFICATION_SUPERCLASS,
					SectorEnum.ADMINISTRATION,
					BigDecimal.valueOf(10000l));
    
    public static ManagerPersonQualification NEW_MANAGER_PERSON_QUALIFICATION =
			new ManagerPersonQualification(OLD_MANAGER_PERSON_QUALIFICATION);
    
    
    public static FullTimeEmployeePersonQualification OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION =
    		new FullTimeEmployeePersonQualification(
    				PERSON_QUALIFICATION_SUPERCLASS,
    				SectorEnum.ADMINISTRATION,
    				BigDecimal.valueOf(10000l));
    
    public static FullTimeEmployeePersonQualification NEW_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION =
    		new FullTimeEmployeePersonQualification(OLD_FULL_TIME_EMPLOYEE_PERSON_QUALIFICATION);
    
    
    public static PartTimeEmployeePersonQualification OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION =
    		new PartTimeEmployeePersonQualification(
    				PERSON_QUALIFICATION_SUPERCLASS,
    				SectorEnum.ADMINISTRATION,
    				BigDecimal.valueOf(100l));
    
    public static PartTimeEmployeePersonQualification NEW_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION =
    		new PartTimeEmployeePersonQualification(OLD_PART_TIME_EMPLOYEE_PERSON_QUALIFICATION);
    
    
    public static ClientPersonQualification OLD_CLIENT_PERSON_QUALIFICATION =
    		new ClientPersonQualification(
    				PERSON_QUALIFICATION_SUPERCLASS,
    				90);
    
    public static ClientPersonQualification NEW_CLIENT_PERSON_QUALIFICATION =
    		new ClientPersonQualification(OLD_CLIENT_PERSON_QUALIFICATION);
    
    
    public static ProviderPersonQualification OLD_PROVIDER_PERSON_QUALIFICATION =
    		new ProviderPersonQualification(
    				PERSON_QUALIFICATION_SUPERCLASS);
   
    public static ProviderPersonQualification NEW_PROVIDER_PERSON_QUALIFICATION =
    		new ProviderPersonQualification(OLD_PROVIDER_PERSON_QUALIFICATION);
    
    
    public static ResponsibleForLegalPersonQualification OLD_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION =
    		new ResponsibleForLegalPersonQualification(
    				PERSON_QUALIFICATION_SUPERCLASS);

    public static ResponsibleForLegalPersonQualification NEW_RESPONSIBLE_FOR_LEGAL_PERSON_QUALIFICATION =
    		new ResponsibleForLegalPersonQualification(
    				PERSON_QUALIFICATION_SUPERCLASS);

    
    public static AccountantPersonQualification OLD_ACCOUNTANT_PERSON_QUALIFICATION =
			new AccountantPersonQualification(
					PERSON_QUALIFICATION_SUPERCLASS,
					BigDecimal.valueOf(10000l));
    
    public static AccountantPersonQualification NEW_ACCOUNTANT_PERSON_QUALIFICATION =
			new AccountantPersonQualification(OLD_ACCOUNTANT_PERSON_QUALIFICATION);
}
