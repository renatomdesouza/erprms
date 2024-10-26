package br.com.erprms.testBuilders;

import java.net.URI;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUsedEnum;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;

@ActiveProfiles("test")
public class Constants_Person {
	public static String VALID_EMAIL = "email@email.com.br";
	public static String INVALID_EMAIL = "";
	
	public static boolean IS_EMAIL_TRUE = true;
	public static boolean IS_EMAIL_FALSE = false;
	
	public static UriComponentsBuilder URI_COMPONENTS_BUILDER = UriComponentsBuilder.fromUri(URI.create("http://unity-test-for-persons"));
	
	public static DtoRecord_NaturalPersonOfRegistry DTO_RECORD_NATURAL_PERSON_OF_REGISTRY = new DtoRecord_NaturalPersonOfRegistry(
			"José da Silva",
			"Silva",
			"12345678912",
			"josesilva@email.com.br",
			"www.silva.com.br",
			"20/01/1980",
			"casado",
			"São Paulo",
			"Brasil",
			"MASCULINE",
			"Rua Das Alamedas",
			"0001",
			"Bairro das Alamedas",
			"não há",
			"01153-000",
			"São Paulo/SP");

	public static DtoRecord_LegalPersonOfRegistry DTO_RECORD_LEGAL_PERSON_OF_REGISTRY = new DtoRecord_LegalPersonOfRegistry(
			"Empresa Jurídica SA",
			"Empresa",
			"12345678912345",
			"empresasa@email.com.br",
			"www.empresasa.com.br",
			"123456789",
			"12345678912345",
			"Rua da Empresa",
			"0001",
			"Bairro Empresarial",
			"Não Possui",
			"01153-000",
			"São Paulo/SP");

	public static PersonEntity NATURAL_PERSON = new PersonEntity(
			1L,
			null,
			StatusPersonalUsedEnum.USED,
			true,
			"José da Silva",
			"Silva",
			12345678912L,
			"josesilva@email.com.br",
			"www.silva.com.br",
			"20/01/1980",
			"casado",
			"São Paulo",
			"Brasil",
			SexEnum.MASCULINE,
			null,
			null,
			"Rua Das Alamedas",
			"0001",
			"Bairro das Alamedas",
			"não há",
			"01153-000",
			"São Paulo/SP",
			null,
			null
	);

	public static PersonEntity LEGAL_PERSON = new PersonEntity(
			2L,
			null,
			StatusPersonalUsedEnum.USED,
			false,
			"Empresa Jurídica SA",
			"Empresa",
			12345678912345L,
			"empresasa@email.com.br",
			"www.empresasa.com.br",
			null,
			null,
			null,
			null,
			null,
			"123456789",
			"12345678912345",
			"Rua da Empresa",
			"0001",
			"Bairro Empresarial",
			"Não Possui",
			"01153-000",
			"São Paulo/SP",
			null,
			null
	);
	
	public static PersonEntity NATURAL_PERSON_NOT_USED = new PersonEntity(
			1L,
			null,
			StatusPersonalUsedEnum.NOT_USED,
			true,
			"José da Silva",
			"Silva",
			12345678912L,
			"josesilva@email.com.br",
			"www.silva.com.br",
			"20/01/1980",
			"casado",
			"São Paulo",
			"Brasil",
			SexEnum.MASCULINE,
			null,
			null,
			"Rua Das Alamedas",
			"0001",
			"Bairro das Alamedas",
			"não há",
			"01153-000",
			"São Paulo/SP",
			null,
			null
	);

	public static PersonEntity LEGAL_PERSON_NOT_USED = new PersonEntity(
			2L,
			null,
			StatusPersonalUsedEnum.NOT_USED,
			false,
			"Empresa Jurídica SA",
			"Empresa",
			12345678912345L,
			"empresasa@email.com.br",
			"www.empresasa.com.br",
			null,
			null,
			null,
			null,
			null,
			"123456789",
			"12345678912345",
			"Rua da Empresa",
			"0001",
			"Bairro Empresarial",
			"Não Possui",
			"01153-000",
			"São Paulo/SP",
			null,
			null
	);
	
	

}


