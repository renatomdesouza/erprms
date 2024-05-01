package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

import java.math.BigDecimal;

public record InputDtoRecord_Accountant(
        Long person_Id,
        BigDecimal monthlyCost,
        String observation,
        String professionalRegistry
) {}
