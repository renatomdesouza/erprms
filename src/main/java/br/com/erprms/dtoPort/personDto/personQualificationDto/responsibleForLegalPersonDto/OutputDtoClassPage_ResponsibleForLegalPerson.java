package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OutputDtoClassPage_ResponsibleForLegalPerson implements PersonQualificationOutputDtoInterface {
    private Long id;
    private String specifiedQualification;
    private Long personId;
    private String personName;
    private String cpfOrCnpj;
    private String observation;
    private LocalDate initialDate;
    private LocalDate finalDate;
}
