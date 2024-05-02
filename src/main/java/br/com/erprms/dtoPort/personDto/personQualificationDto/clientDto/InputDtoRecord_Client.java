package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto;

public record InputDtoRecord_Client(
        Long person_Id,
        String observation,
        String professionalRegistry,
        Integer creditDays
) {}
