package br.com.erprms.infrastructure.exceptionManager.responseStatusException;

import static org.junit.jupiter.api.Assertions.*;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
class PersonExceptionsTest {
	@Test
	@DisplayName("Should throw an exception when receiving a string representative of an email")
	void unitTest_ThrowExceptionForPresentEmail() {
		assertThrows(ResponseStatusException.class,
				() -> new PersonExceptions().existingEmailException(String.valueOf(Void.TYPE)));
	}

	@Test
	@DisplayName("Should not throw an exception when not receiving a string representative of an email")
	void unitTest_ThrowExceptionForPresentNotEmail() {
		assertDoesNotThrow(
				() -> new PersonExceptions().existingEmailException(null));
	}

	@Test
	@DisplayName("Should not launch when the registry is in use")
	void unitTest_ThrowExceptionIsInUse() {
		assertThrows(ResponseStatusException.class,
				() -> new PersonExceptions().personWithStatusInUse(StatusPersonalUseEnum.USED));
	}

	@Test
	@DisplayName("Should not launch when the registry is not in use")
	void unitTest_ThrowExceptionForIsNotUse() {
		assertDoesNotThrow(
				() -> new PersonExceptions().personWithStatusInUse(StatusPersonalUseEnum.NOT_USED));
    }
}
