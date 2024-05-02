package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoClass_Accountant;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OutputDtoClass_Provider implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private String cpfOrCnpj;
    private String observation;

    public OutputDtoClass_Provider(
            PersonEntity person,
            InputDtoClass_Provider provider,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = person.getFullNameOrEntityName();
        this.cpfOrCnpj = person.getCpfOrCnpj();
        this.observation = provider.getObservation();
    }

}
