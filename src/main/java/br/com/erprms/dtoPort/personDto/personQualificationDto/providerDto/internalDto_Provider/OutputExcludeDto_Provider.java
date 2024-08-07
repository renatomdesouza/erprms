package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputExcludeDto_Provider implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;

    public OutputExcludeDto_Provider(
            PersonQualificationSuperclassEntity clientToDelete,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = clientToDelete.getPerson().getFullNameOrEntityName();
        this.cpfOrCnpj = clientToDelete.getPerson().getCpfOrCnpj();
        this.observation = clientToDelete.getObservation();
    }
}
