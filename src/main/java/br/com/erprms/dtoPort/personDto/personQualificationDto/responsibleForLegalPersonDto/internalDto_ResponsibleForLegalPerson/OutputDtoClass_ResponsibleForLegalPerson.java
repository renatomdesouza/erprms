package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputDtoClass_ResponsibleForLegalPerson implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;

    public OutputDtoClass_ResponsibleForLegalPerson(
            PersonEntity person,
            InputDtoClass_ResponsibleForLegalPerson responsible,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = person.getFullNameOrEntityName();
        this.cpfOrCnpj = person.getCpfCnpj();
        this.observation = responsible.getObservation();
    }
}
