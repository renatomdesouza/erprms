package br.com.erprms.infrastructure.exceptionManager.responseStatusException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;

@ExtendWith(MockitoExtension.class)
class PersonExceptionsTest {
	@Test
	@DisplayName("Should throw an exception when receiving a string representative of an email")
	void unitTest_ThrowExceptionForPresentEmail() {
		final ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> new PersonExceptions().existingEmailException(String.valueOf(Void.TYPE)));

		assertThat(ex, notNullValue());
		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"The user's email is already registered in the system\""));
	}

	@Test
	@DisplayName("Should throw an exception when not receiving a string representative of an email")
	void unitTest_ThrowExceptionForNotPresentEmail() {
		assertDoesNotThrow(() -> new PersonExceptions().existingEmailException(null));
	}

	@Test
	@DisplayName("Should not launch when the registry is in use")
	void unitTest_ThrowExceptionIsInUse() {
		final ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> new PersonExceptions().personWithStatusInUse(StatusPersonalUseEnum.USED));

		assertThat(ex, notNullValue());
		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"This person cannot be deleted because there is a qualification registered for them\""));
	}

	@Test
	@DisplayName("Should not launch when the registry is not in use")
	void unitTest_ThrowExceptionForIsNotUse() {
		assertDoesNotThrow(() -> new PersonExceptions().personWithStatusInUse(StatusPersonalUseEnum.NOT_USED));
    }
}
