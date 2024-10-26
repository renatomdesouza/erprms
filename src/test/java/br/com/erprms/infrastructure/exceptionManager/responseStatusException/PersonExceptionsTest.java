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

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;

@ExtendWith(MockitoExtension.class)
class PersonExceptionsTest {
	@Test
	@DisplayName("Should throw an exception when receiving a string representative of an email")
	void unitTest_ThrowExceptionForPresentEmail() {
		final boolean IS_EMAIL_TRUE = true;

		final ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> new PersonExceptions().existingEmailException(IS_EMAIL_TRUE));

		assertThat(ex, notNullValue());
		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"The user's email is already registered in the system - They cannot be duplicated or altered\""));
	}

	@Test
	@DisplayName("Should throw an exception when not receiving a string representative of an email")
	void unitTest_ThrowExceptionForNotPresentEmail() {
		final boolean IS_EMAIL_FALSE = false;

		assertDoesNotThrow(() -> new PersonExceptions().existingEmailException(IS_EMAIL_FALSE));
	}

	@Test
	@DisplayName("Should not launch when the registry is in use")
	void unitTest_ThrowExceptionIsInUse() {
		final ResponseStatusException ex = assertThrows(
				ResponseStatusException.class,
				() -> new PersonExceptions().personWithStatusInUse(StatusPersonalUsedEnum.USED));

		assertThat(ex, notNullValue());
		assertThat(ex.getMessage(), is("507 INSUFFICIENT_STORAGE \"This person cannot be deleted because there is a qualification registered for them\""));
	}

	@Test
	@DisplayName("Should not launch when the registry is not in use")
	void unitTest_ThrowExceptionForIsNotUse() {
		assertDoesNotThrow(() -> new PersonExceptions().personWithStatusInUse(StatusPersonalUsedEnum.NOT_USED));
    }
}
