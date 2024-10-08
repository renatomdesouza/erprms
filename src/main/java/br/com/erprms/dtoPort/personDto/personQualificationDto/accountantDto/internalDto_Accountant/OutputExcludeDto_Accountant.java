package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.internalDto_Accountant;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputExcludeDto_Accountant implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;

    public OutputExcludeDto_Accountant(
            PersonQualificationSuperclassEntity accountantToDelete,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = accountantToDelete.getPerson().getFullNameOrEntityName();
        this.cpfOrCnpj = accountantToDelete.getPerson().getCpfCnpj();
        this.observation = accountantToDelete.getObservation();
    }
}
