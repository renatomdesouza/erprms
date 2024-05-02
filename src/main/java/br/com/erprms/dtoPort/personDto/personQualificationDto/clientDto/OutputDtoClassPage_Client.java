package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto;

import java.time.LocalDate;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputDtoClassPage_Client implements PersonQualificationOutputDtoInterface {
    private Long id;
    private String specifiedQualification;
    private Long personId;
    private String personName;
    private String cpfOrCnpj;
    private Integer creditDays;
    private String observation;
    private LocalDate initialDate;
    private LocalDate finalDate;
}
