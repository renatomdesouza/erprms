package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto;

public record InputDtoRecord_Provider(
        Long person_Id,
        String observation,
        String professionalRegistry
    ) {}
