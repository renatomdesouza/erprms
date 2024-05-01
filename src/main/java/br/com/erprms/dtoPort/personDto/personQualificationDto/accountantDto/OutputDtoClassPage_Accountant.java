package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OutputDtoClassPage_Accountant  implements PersonQualificationOutputDtoInterface {
    private Long id;
    private String specifiedQualification;
    private Long personId;
    private String personName;
    private String cpfOrCnpj;
    private BigDecimal monthlyCost;
    private String observation;
    private String professionalRegistry;
    private LocalDate initialDate;
    private LocalDate finalDate;
}
