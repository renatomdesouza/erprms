package br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson.DtoClass_LegalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson.DtoClass_NaturalPersonOfRegistry;

@Service
public class CreatePersonFromDto_Service {
	private ModelMapper mapper;
	
	public CreatePersonFromDto_Service(
			ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public <T> PersonEntity create(T personDto) {
		var person = new PersonEntity();

		if (personDto instanceof DtoRecord_NaturalPersonOfRegistry) {
			DtoClass_NaturalPersonOfRegistry dtoNaturalPerson =
				new DtoClass_NaturalPersonOfRegistry((DtoRecord_NaturalPersonOfRegistry) personDto);

			person = this.mapper.map(dtoNaturalPerson, PersonEntity.class);
		};

		if (personDto instanceof DtoRecord_LegalPersonOfRegistry) {
				DtoClass_LegalPersonOfRegistry dtoLegalPerson =
						new DtoClass_LegalPersonOfRegistry((DtoRecord_LegalPersonOfRegistry) personDto);

				person = this.mapper.map(dtoLegalPerson, PersonEntity.class);
		};

		return person;
	}

}
