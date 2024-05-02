package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputDtoClass_ResponsibleForLegalPerson implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final String observation;
    private final String professionalRegistry;

    public InputDtoClass_ResponsibleForLegalPerson(InputDtoRecord_ResponsibleForLegalPerson responsible) {
        this.person_Id = responsible.person_Id();
        this.observation = responsible.observation();
        this.professionalRegistry = responsible.professionalRegistry();
    }
}
