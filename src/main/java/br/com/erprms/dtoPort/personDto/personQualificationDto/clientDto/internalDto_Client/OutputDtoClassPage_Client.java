package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client;

import java.time.LocalDateTime;

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
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
}
