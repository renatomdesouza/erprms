package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto;

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

class InputDtoRecord_FullTimeEmployeeAndManagerTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/FullTimeEmployeeAndManagerFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids creation of manger and full time employee")
	void unitParameterizedTest_InvalidCreationOfFullTimeEmployeeAndManager(String person_Id, String monthlySalary, String sector, String observation, String professionalRegistry) {
		var dto = new InputDtoRecord_FullTimeEmployeeAndManager(	person_Id,
																	monthlySalary,
																	sector,
																	observation,
																	professionalRegistry);

		Set<ConstraintViolation<InputDtoRecord_FullTimeEmployeeAndManager>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should be valid for accountant with correct creation manger and full time employee")
	void unitTest_ValidCreationOfFullTimeEmployeeAndManager() {
		var dto = new InputDtoRecord_FullTimeEmployeeAndManager("01",
				"1000.01",
				"ADMINISTRATION",
				"Observação qualquer",
				"25687" );

		Set<ConstraintViolation<InputDtoRecord_FullTimeEmployeeAndManager>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}
}
