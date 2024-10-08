package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputDtoClass_Provider implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;

    public OutputDtoClass_Provider(
            PersonEntity person,
            InputDtoClass_Provider provider,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = person.getFullNameOrEntityName();
        this.cpfOrCnpj = person.getCpfCnpj();
        this.observation = provider.getObservation();
    }

}
