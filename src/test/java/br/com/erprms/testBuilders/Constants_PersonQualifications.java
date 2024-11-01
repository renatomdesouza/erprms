package br.com.erprms.testBuilders;

import static br.com.erprms.testBuilders.Constants_Person.NATURAL_PERSON;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.test.context.ActiveProfiles;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.employeePersonQualificator.ManagerPersonQualification;
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
    
    public static ManagerPersonQualification MANAGER_PERSON_QUALIFICATION =
			new ManagerPersonQualification(
					PERSON_QUALIFICATION_SUPERCLASS,
					SectorEnum.ADMINISTRATION,
					BigDecimal.valueOf(10000l))
			;

}
