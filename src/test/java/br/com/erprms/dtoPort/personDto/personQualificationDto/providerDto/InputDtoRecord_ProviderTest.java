package br.com.erprms.dtoPort.personDto.personQualificationDto.providerDto;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class InputDtoRecord_ProviderTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/ProviderFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids updates of provider")
	void unitParameterizedTest_InvalidCreationOfProvider(String person_Id, String observation, String professionalRegistry) {
		var dto = new InputDtoRecord_Provider(	person_Id,
												observation,
												professionalRegistry);

		Set<ConstraintViolation<InputDtoRecord_Provider>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should be valid for accountant with correct creation of provider")
	void unitTest_ValidCreationOfProvider() {
		var dto = new InputDtoRecord_Provider(	"01",
												"Observação qualquer",
												"25687" );

		Set<ConstraintViolation<InputDtoRecord_Provider>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}
}
