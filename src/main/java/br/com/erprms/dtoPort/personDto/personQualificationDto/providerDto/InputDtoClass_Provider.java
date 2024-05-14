package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import lombok.Getter;

@Getter
public class InputDtoClass_Provider implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final String observation;
    private final String professionalRegistry;

    public InputDtoClass_Provider(InputDtoRecord_Provider provider) {
        this.person_Id = provider.person_Id();
        this.observation = provider.observation();
        this.professionalRegistry = provider.professionalRegistry();
    }
}
