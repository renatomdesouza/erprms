package br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Wrapper;
import java.util.Optional;

@Getter
public class DtoClass_NaturalPersonOfRegistry {
	private String fullNameOrEntityName;
	private String nickname;
	private Long cpf;
	private String email;
	private String site;
	private String dateBorn;
	private String maritalStatus;
	private String cityBorn;
	private String countryBorn;
	private SexEnum sex;
	private String street;
	private String number;
	private String neighborhood;
	private String complement;
	private String postalCode;
	private String cityAndStateOrProvince;

	StatusPersonalUseEnum statusPersonEnum;

	Boolean isNaturalPerson;

	@Autowired
	PersonRepository repository;
	
	public DtoClass_NaturalPersonOfRegistry (DtoRecord_NaturalPersonOfRegistry naturalPerson) {
		this.fullNameOrEntityName = naturalPerson.fullNameOrEntityName();
		this.nickname = naturalPerson.nickname();
		this.cpf =
                Long.parseLong(naturalPerson.cpf());

		this.email = naturalPerson.email();
		this.site = naturalPerson.site();
		this.dateBorn = naturalPerson.dateBorn();
		this.maritalStatus = naturalPerson.maritalStatus();
		this.cityBorn = naturalPerson.cityBorn();
		this.countryBorn = naturalPerson.countryBorn();
		this.sex =
				SexEnum.valueOf(naturalPerson.sex());

		this.street = naturalPerson.street();
		this.number = naturalPerson.number();
		this.neighborhood = naturalPerson.neighborhood();
		this.complement = naturalPerson.complement();
		this.postalCode = naturalPerson.postalCode();
		this.cityAndStateOrProvince = naturalPerson.cityAndStateOrProvince();
		
		this.statusPersonEnum = StatusPersonalUseEnum.NOT_USED;
		this.isNaturalPerson = true;

//		checksPreviousExistenceOfCpf(this.cpf);
//		checksPreviousExistenceOfEmail(this.email);
	}

	private void checksPreviousExistenceOfCpf(Long cpf){
		var cpfUsed = repository.findByCpfCnpj(cpf);
		System.out.println(" ===> " + cpfUsed);
		var cpfOptional = Optional.of(cpfUsed);
		if (cpfOptional.isPresent())
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE,
					"There is already a person with this CPF") ;
	}

	private void checksPreviousExistenceOfEmail(String email){
		var emailUsed = repository.findEmail(email);
		System.out.println(" ===> " + emailUsed);
		var emailOptional = Optional.of(emailUsed);
		if (emailOptional.isPresent())
			throw new ResponseStatusException(
					HttpStatus.INSUFFICIENT_STORAGE,
					"There is already a person with this email") ;
	}

}
