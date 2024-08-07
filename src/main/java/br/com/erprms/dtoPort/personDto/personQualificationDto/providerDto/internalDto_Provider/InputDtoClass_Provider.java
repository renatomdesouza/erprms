package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.internalDto_Provider;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto.InputDtoRecord_Provider;
import lombok.Getter;

@Getter
public class InputDtoClass_Provider implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final String observation;
    private final String professionalRegistry;

    public InputDtoClass_Provider(InputDtoRecord_Provider provider) {
        this.person_Id =
                Long.parseLong(provider.person_Id());

        this.observation = provider.observation();
        this.professionalRegistry = provider.professionalRegistry();
    }
}
