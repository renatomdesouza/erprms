package br.com.erprms.dtoPort.personDto.naturalPersonDto;

import static br.com.erprms.testBuilders.NaturalPersonTestBuilder.naturalPersonTestBuilder;
import static org.junit.jupiter.api.Assertions.*;

import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfUpdate;
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
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

class DtoRecord_NaturalPersonOfUpdateTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest
	@CsvSource(	value = "01~José da Silva~Silva~12345678912~josesilva@email.com.br~www.silva.com.br~20/01/1980~casado~São Paulo~São Paulo~MASCULINE~Rua Das Alamedas~0001~Bairro das Alamedas~não há~01153-000~São Paulo/SP",
				delimiter = '~')
	@DisplayName("Should be valid for Natural Person with correct update")
	void unitTest_ValidUpdateOfNaturalPerson(String id,
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
											 String cityAndStateOrProvince) {
		var dto = new DtoRecord_NaturalPersonOfUpdate( 	id,
				fullNameOrEntityName,
														nickname,
														cpf,
														email,
														site,
														dateBorn,
														maritalStatus,
														cityBorn,
														countryBorn,
														sex,
														street,
														number,
														neighborhood,
														complement,
														postalCode,
														cityAndStateOrProvince);

		Set<ConstraintViolation<DtoRecord_NaturalPersonOfUpdate>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}

	@ParameterizedTest
	@CsvFileSource(files = "src/test/resources/NaturalPersonUpdateFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids updates of Natural Person")
	void unitParameterizedTest_InvalidUpdateOfNaturalPerson( 	String id,
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
		var dto = new DtoRecord_NaturalPersonOfUpdate( 	id,
														fullNameOrEntityName,
														nickname,
														cpf,
														email,
														site,
														dateBorn,
														maritalStatus,
														cityBorn,
														countryBorn,
														sex,
														street,
														number,
														neighborhood,
														complement,
														postalCode,
														cityAndStateOrProvince);

		Set<ConstraintViolation<DtoRecord_NaturalPersonOfUpdate>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}
}
