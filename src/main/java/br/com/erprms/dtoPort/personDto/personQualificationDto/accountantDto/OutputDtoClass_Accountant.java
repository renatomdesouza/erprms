package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OutputDtoClass_Accountant implements PersonQualificationOutputDtoInterface {
    private String specifiedQualification;
    private String personName;
    private Long cpfOrCnpj;
    private String observation;
    private String professionalRegistry;

    private BigDecimal monthlyCost;
    private SectorEnum sector;

    public OutputDtoClass_Accountant(
            PersonEntity person,
            InputDtoClass_Accountant accountant,
            String specifiedQualification) {
        this.specifiedQualification = specifiedQualification;
        this.personName = person.getFullNameOrEntityName();
        this.cpfOrCnpj = person.getCpfOrCnpj();
        this.observation = accountant.getObservation();
        this.professionalRegistry = accountant.getProfessionalRegistry();
        this.monthlyCost = accountant.getMonthlyCost();
    }
}
