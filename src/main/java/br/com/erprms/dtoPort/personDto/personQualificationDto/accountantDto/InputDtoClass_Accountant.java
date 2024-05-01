package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoRecord_FullTimeEmployeeAndManager;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class InputDtoClass_Accountant  implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final BigDecimal monthlyCost;
    private final String observation;
    private final String professionalRegistry;

    public InputDtoClass_Accountant(InputDtoRecord_Accountant accountant) {
        this.person_Id = accountant.person_Id();
        this.monthlyCost = accountant.monthlyCost();
        this.observation = accountant.observation();
        this.professionalRegistry = accountant.professionalRegistry();
    }
}
