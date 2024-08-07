package br.com.erprms.dtoPort.personDto.personQualificationDto.clientDto;

import static org.junit.jupiter.api.Assertions.*;

import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoRecord_Accountant;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Set;

class InputDtoRecord_ClientTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/ClientFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids updates of client")
	void unitParameterizedTest_InvalidCreationOfClient(String person_Id, String observation, String professionalRegistry, String creditDays) {
		var dto = new InputDtoRecord_Client(	person_Id,
												observation,
												professionalRegistry,
												creditDays);

		Set<ConstraintViolation<InputDtoRecord_Client>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should be valid for accountant with correct creation of client")
	void unitTest_ValidCreationOfClient() {
		var dto = new InputDtoRecord_Client(	"01",
												"Observação qualquer",
												"25687",
												"30");

		Set<ConstraintViolation<InputDtoRecord_Client>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}
}
