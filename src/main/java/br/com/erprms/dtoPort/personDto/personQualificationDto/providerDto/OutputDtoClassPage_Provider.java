package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OutputDtoClassPage_Provider implements PersonQualificationOutputDtoInterface {
    private Long id;
    private String specifiedQualification;
    private Long personId;
    private String personName;
    private String cpfOrCnpj;
    private String observation;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
}
