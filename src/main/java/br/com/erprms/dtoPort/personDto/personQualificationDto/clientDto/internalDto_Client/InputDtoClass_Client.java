package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.internalDto_Client;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto.InputDtoRecord_Client;
import lombok.Getter;

@Getter
public class InputDtoClass_Client  implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final String observation;
    private final Integer creditTerms;

    public InputDtoClass_Client(InputDtoRecord_Client client) {
        this.person_Id =
                Long.parseLong(client.person_Id());

        this.observation = client.observation();
        this.creditTerms =
                Integer.parseInt(client.creditDays());
    }
}
