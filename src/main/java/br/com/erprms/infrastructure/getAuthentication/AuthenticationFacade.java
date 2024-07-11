package br.com.erprms.infrastructure.getAuthentication;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthenticationFacade implements AuthenticationFacadeInterface {

    @Override
    public String getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken)
            throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE, "Unauthenticated user");

        return authentication.getName();
    }
}
