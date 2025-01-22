package br.com.erprms.infrastructure.exceptionManager.responseStatusException;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;

@Service
public class PersonExceptions {

    public void personWithStatusInUse(StatusPersonalUsedEnum statusPerson) {
        if ( statusPerson == StatusPersonalUsedEnum.USED )
            throw new ResponseStatusException(
                    HttpStatus.INSUFFICIENT_STORAGE,
                    "This person cannot be deleted because there is a qualification registered for them") ;
    }

    public void existingEmailException(boolean isEmail) {
        if ( isEmail )
            throw new ResponseStatusException(
                    HttpStatus.INSUFFICIENT_STORAGE,
                    "The user's email is already registered in the system - They cannot be duplicated or altered");
    }

    public void existingEmailException_02(String email) {
        Optional<String> emailOptimal = Optional.ofNullable(email);
        if ( emailOptimal.isPresent() )
            throw new ResponseStatusException(
                    HttpStatus.INSUFFICIENT_STORAGE,
                    "The user's email is already registered in the system");
    }
}