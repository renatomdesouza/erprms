package br.com.erprms.dtoPort.personDto.legalPersonDto;

import static org.junit.jupiter.api.Assertions.*;

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

class DtoRecord_LegalPersonOfUpdateTest {
	Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ParameterizedTest // The simple test with input Record could have been used, but this form was chosen to exemplify @CsvSource
	@CsvSource(	value = {"01~Empresa Jurídica SA~Empresa~12345678912345~empresasa@email.com.br~www.empresasa.com.br~123456789~12345678912345~Rua da Empresa~0001~Bairro Empresarial~Não Possui~01153-000~São Paulo/SP"},
			delimiter = '~')
	@DisplayName("Should be valid for Legal Person with correct update")
	void unitTest_ValidUpdateOfLegalPerson(		String id,
												String fullNameOrEntityName,
												String nickname,
												String cnpj,
												String email,
												String site,
												String inscricEstad,
												String inscricMunicip,
												String street,
												String number,
												String neighborhood,
												String complement,
												String postalCode,
												String cityAndStateOrProvince) {
		var dto = new DtoRecord_LegalPersonOfUpdate( 	id,
														fullNameOrEntityName,
														nickname,
														cnpj,
														email,
														site,
														inscricEstad,
														inscricMunicip,
														street,
														number,
														neighborhood,
														complement,
														postalCode,
														cityAndStateOrProvince);

		Set<ConstraintViolation<DtoRecord_LegalPersonOfUpdate>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertTrue(violations.isEmpty());
	}

	@ParameterizedTest
	@CsvFileSource(	files = "src/test/resources/LegalPersonUpdateFields.csv",
			nullValues = "NULL",
			delimiter = '~',
			numLinesToSkip = 1)
	@DisplayName("Should be invalid containing \"ConstraintViolation\" for invalids updates of Legal Person")
	void unitTest_InvalidUpdateOfLegalPerson(	String id,
												String fullNameOrEntityName,
												String nickname,
												String cnpj,
												String email,
												String site,
												String inscricEstad,
												String inscricMunicip,
												String street,
												String number,
												String neighborhood,
												String complement,
												String postalCode,
												String cityAndStateOrProvince) {
		var dto = new DtoRecord_LegalPersonOfUpdate( 	id,
														fullNameOrEntityName,
														nickname,
														cnpj,
														email,
														site,
														inscricEstad,
														inscricMunicip,
														street,
														number,
														neighborhood,
														complement,
														postalCode,
														cityAndStateOrProvince);

		Set<ConstraintViolation<DtoRecord_LegalPersonOfUpdate>> violations = validator.validate(dto);
		violations.iterator().forEachRemaining(System.out::println);

		Assertions.assertFalse(violations.isEmpty());
	}
}
