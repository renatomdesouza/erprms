package br.com.erprms.infrastructure.exceptionManager.responseStatusException;

import java.util.Optional;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonExceptions {

    public void personWithStatusInUse(StatusPersonalUseEnum statusPerson) {
        if ( statusPerson == StatusPersonalUseEnum.USED )
            throw new ResponseStatusException(
                    HttpStatus.INSUFFICIENT_STORAGE,
                    "This person cannot be deleted because there is a qualification registered for them") ;
    }

    public void existingEmailException(String oldEmail) {
        Optional<String> emailOptimal = Optional.ofNullable(oldEmail);
        if(emailOptimal.isPresent())
            throw new ResponseStatusException(
                    HttpStatus.INSUFFICIENT_STORAGE,
                    "The user's email is already registered in the system");
    }
}
