package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputExcludeDto_ResponsibleForLegalPerson implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;

    public OutputExcludeDto_ResponsibleForLegalPerson(
            PersonQualificationSuperclassEntity responsibleToDelete,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = responsibleToDelete.getPerson().getFullNameOrEntityName();
        this.cpfOrCnpj = responsibleToDelete.getPerson().getCpfOrCnpj();
        this.observation = responsibleToDelete.getObservation();
    }
}
