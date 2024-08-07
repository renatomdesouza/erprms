package br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto;

import static org.junit.jupiter.api.Assertions.*;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.InputDtoRecord_FullTimeEmployeeAndManager;
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

class InputDtoRecord_PartTimeEmployeeTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/PartTimeEmployeeFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids creation of part time employee")
	void unitParameterizedTest_InvalidCreationOfPartimeEmployeer(String person_Id, String hourlyRate, String sector, String observation, String professionalRegistry) {
		var dto = new InputDtoRecord_PartTimeEmployee(	person_Id,
														hourlyRate,
														sector,
														observation,
														professionalRegistry);

		Set<ConstraintViolation<InputDtoRecord_PartTimeEmployee>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should be valid for accountant with correct creation part time employee")
	void unitTest_ValidCreationOfPartTimeEmployee() {
		var dto = new InputDtoRecord_PartTimeEmployee(	"01",
														"1000.01",
														"ADMINISTRATION",
														"Observação qualquer",
														"25687" );

		Set<ConstraintViolation<InputDtoRecord_PartTimeEmployee>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}
}
