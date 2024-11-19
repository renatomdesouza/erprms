package br.com.erprms.dtoPort.personDto.naturalPersonDto;

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

import static br.com.erprms.constantsAndBuilder.Builder_NaturalPersonTest.naturalPersonTestBuilder;

import java.util.Set;

class DtoRecord_NaturalPersonOfRegistryTest {
	Validator validator;

	@BeforeEach
    public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@DisplayName("Should be valid for Natural Person with correct creation")
	void unitTest_ValidCreationOfNaturalPerson() {
		DtoRecord_NaturalPersonOfRegistry dto =
				naturalPersonTestBuilder().build(); //This builder pattern object would no longer be necessary after the refactoring to the parameterized test below, but it was kept as an example.

		Set<ConstraintViolation<DtoRecord_NaturalPersonOfRegistry>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}

    @ParameterizedTest
    @CsvFileSource(	files = "src/test/resources/NaturalPersonFields.csv",
					nullValues = "NULL",
					delimiter = '~',
					numLinesToSkip = 1)
    @DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids creations of Natural Person")
    void unitParameterizedTest_InvalidCreationOfNaturalPerson(
            String fullNameOrEntityName,
            String nickname,
            String cpf,
            String email,
            String site,
            String dateBorn,
            String maritalStatus,
            String cityBorn,
            String countryBorn,
            String sex,
            String street,
            String number,
            String neighborhood,
            String complement,
            String postalCode,
            String cityAndStateOrProvince
    ) {
        DtoRecord_NaturalPersonOfRegistry dto =
                naturalPersonTestBuilder() //This builder pattern object would no longer be necessary after the refactoring to this parameterized test, but it was kept as an example.
						.fullNameOrEntityName(fullNameOrEntityName)
                        .nickname(nickname)
                        .cpf(cpf)
                        .email(email)
                        .site(site)
                        .dateBorn(dateBorn)
                        .maritalStatus(maritalStatus)
                        .cityBorn(cityBorn)
                        .countryBorn(countryBorn)
                        .sex(sex)
                        .street(street)
                        .number(number)
                        .neighborhood(neighborhood)
                        .complement(complement)
                        .postalCode(postalCode)
                        .cityAndStateOrProvince(cityAndStateOrProvince)
                        .build();

        Set<ConstraintViolation<DtoRecord_NaturalPersonOfRegistry>> violations = validator.validate(dto);
        violations.iterator().forEachRemaining(System.out::println);

        Assertions.assertFalse(violations.isEmpty());
    }
}
