package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto;

import java.time.Period;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationInputDtoInterface;
import lombok.Getter;

@Getter
public class InputDtoClass_Client  implements PersonQualificationInputDtoInterface {
    private final Long person_Id;
    private final String observation;
    private final Integer creditTerms;

    public InputDtoClass_Client(InputDtoRecord_Client client) {
        this.person_Id = client.person_Id();
        this.observation = client.observation();
        this.creditTerms = client.creditDays();
    }
}
