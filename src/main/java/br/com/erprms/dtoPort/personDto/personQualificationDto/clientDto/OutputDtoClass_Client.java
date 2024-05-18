package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutputDtoClass_Client implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;
    private Integer creditDays;

    public OutputDtoClass_Client(
            PersonEntity person,
            InputDtoClass_Client client,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = person.getFullNameOrEntityName();
        this.cpfOrCnpj = person.getCpfOrCnpj();
        this.observation = client.getObservation();
        this.creditDays = client.getCreditTerms();
    }
}
