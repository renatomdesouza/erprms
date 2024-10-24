package br.com.erprms.serviceApplication.personService.personHttpVerbService;

import org.modelmapper.ModelMapper;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.dtoPort.personDto.PersonListingDto;
import br.com.erprms.dtoPort.personDto.legalPersonDto.internalDto_LegalPerson.DtoClass_LegalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson.DtoClass_NaturalPersonOfListing;
import br.com.erprms.dtoPort.personDto.naturalPersonDto.internalDto_LegalPerson.DtoClass_NaturalPersonOfUpdate;

public class PersonService_CreateDto <T extends PersonListingDto> {
	private ModelMapper mapper;
	
	public PersonService_CreateDto(ModelMapper mapper) { 
		this.mapper = mapper;
	}

	@SuppressWarnings("unchecked")
	public T selectNaturalOrLegalPersonToListing_Dto(PersonEntity person) {

		T personListingDto = null; 
		
		if(person.getIsNaturalPerson())  
			personListingDto = (T) mapper.map(person, DtoClass_NaturalPersonOfListing.class); 

		if(!person.getIsNaturalPerson())
			personListingDto = (T) mapper.map(person, DtoClass_LegalPersonOfListing.class);
		
		return personListingDto;
	}
	
	@SuppressWarnings("unchecked")
	public T selectNaturalOrLegalPersonToListing(PersonEntity person, DtoClass_NaturalPersonOfUpdate naturalPersonDtoOfClass) {

		T dtoClassTolPerson_ToListing = null; 
		
		if(person.getIsNaturalPerson()) 
			dtoClassTolPerson_ToListing = (T) naturalPersonDtoOfClass;  

		if(!person.getIsNaturalPerson()) 
			dtoClassTolPerson_ToListing = (T) naturalPersonDtoOfClass;
		
		return dtoClassTolPerson_ToListing;
	}
}
