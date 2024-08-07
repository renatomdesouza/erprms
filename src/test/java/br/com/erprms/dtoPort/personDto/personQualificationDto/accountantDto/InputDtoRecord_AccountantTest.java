package br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto;

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

class InputDtoRecord_AccountantTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/AccountantFields.csv",
					nullValues = "NULL",
					delimiter = '~',
					numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids updates of accountant")
	void unitParameterizedTest_InvalidCreationOfAccountant(String person_Id, String monthlyCost, String observation, String professionalRegistry) {
		var dto = new InputDtoRecord_Accountant(	person_Id,
													monthlyCost,
													observation,
													professionalRegistry);

		Set<ConstraintViolation<InputDtoRecord_Accountant>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should be valid for accountant with correct creation")
	void unitTest_ValidCreationOfAccountant() {
		var dto = new InputDtoRecord_Accountant("01",
				"1000.01",
				"Observação qualquer",
				"25687" );

		Set<ConstraintViolation<InputDtoRecord_Accountant>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}
}
