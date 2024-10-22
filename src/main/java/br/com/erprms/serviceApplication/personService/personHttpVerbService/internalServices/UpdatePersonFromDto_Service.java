package br.com.erprms.serviceApplication.personService.personHttpVerbService.internalServices;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.legalPersonDto.DtoRecord_LegalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson.DtoClass_LegalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfUpdate;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson.DtoClass_NaturalPersonOfUpdate;

@Service
public class UpdatePersonFromDto_Service {
	private ModelMapper mapper;
	
	public UpdatePersonFromDto_Service(ModelMapper mapper) {
		this.mapper = mapper;
	}

	public <T> PersonEntity update(T personDtoOfRecord, PersonEntity person) {
		if (personDtoOfRecord instanceof DtoRecord_NaturalPersonOfUpdate) {
			var personUpdateDto = new DtoClass_NaturalPersonOfUpdate((DtoRecord_NaturalPersonOfUpdate) personDtoOfRecord);

			mapper.map(personUpdateDto, person);
		};
		
		if ( personDtoOfRecord instanceof DtoRecord_LegalPersonOfUpdate) {
			var personUpdateDto = new DtoClass_LegalPersonOfUpdate((DtoRecord_LegalPersonOfUpdate) personDtoOfRecord);

			mapper.map(personUpdateDto, person);
		};
		
		return person;
	}	
}
