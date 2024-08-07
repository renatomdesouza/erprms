package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.InputDtoRecord_ResponsibleForLegalPerson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputDtoClass_ResponsibleForLegalPerson implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final String observation;
    private final String professionalRegistry;

    public InputDtoClass_ResponsibleForLegalPerson(InputDtoRecord_ResponsibleForLegalPerson responsible) {
        this.person_Id =
                Long.parseLong(responsible.person_Id());

        this.observation = responsible.observation();
        this.professionalRegistry = responsible.professionalRegistry();
    }
}
