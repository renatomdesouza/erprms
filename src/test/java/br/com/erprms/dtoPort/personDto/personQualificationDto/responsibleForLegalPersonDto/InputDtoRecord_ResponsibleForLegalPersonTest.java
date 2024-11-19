package br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto;

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

class InputDtoRecord_ResponsibleForLegalPersonTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/ResponsibleForLegalPersonFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids updates of responsible for legal person")
	void unitParameterizedTest_InvalidCreationOfResponsibleForLegalPerson(String person_Id, String observation, String professionalRegistry) {
		var dto = new InputDtoRecord_ResponsibleForLegalPerson(	person_Id,
																observation,
																professionalRegistry);

		Set<ConstraintViolation<InputDtoRecord_ResponsibleForLegalPerson>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should be valid for accountant with correct creation of responsible for legal person")
	void unitTest_ValidCreationOfResponsibleForLegalPerson() {
		var dto = new InputDtoRecord_ResponsibleForLegalPerson(	"01",
																"Observação qualquer",
																"25687" );

		Set<ConstraintViolation<InputDtoRecord_ResponsibleForLegalPerson>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}
}
