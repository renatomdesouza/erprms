package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

import java.math.BigDecimal;

public record InputDtoRecord_Accountant(
        Long person_Id,
        BigDecimal monthlyCost,
        String observation,
        String professionalRegistry
) {}
