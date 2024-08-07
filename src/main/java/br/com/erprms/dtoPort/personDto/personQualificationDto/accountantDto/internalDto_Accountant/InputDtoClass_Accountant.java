package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant;

import java.math.BigDecimal;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoRecord_Accountant;
import lombok.Getter;

@Getter
public class InputDtoClass_Accountant  implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final BigDecimal monthlyCost;
    private final String observation;
    private final String professionalRegistry;

    public InputDtoClass_Accountant(InputDtoRecord_Accountant accountant) {
        this.person_Id =
                Long.parseLong(accountant.person_Id())   ;
        this.monthlyCost =
                BigDecimal.valueOf(
                        Long.parseLong(accountant.monthlyCost()));
        this.observation = accountant.observation();
        this.professionalRegistry = accountant.professionalRegistry();
    }
}
