package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erprms.dtoPort.personDto.managerDto.DtoClass_ListingOfQualification;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualifService_HttpGet {
	private ManagerRepository managerRepository;
	
	public PersonQualifService_HttpGet(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}
	
	@Transactional   
	public Page<DtoClass_ListingOfQualification> listingService(
			String qualification,
			Pageable qualificationPageable) {  
		return managerRepository
				.findEmployeePersonQualificationSubclassByFinalDateIsNull(qualificationPageable)
				.map(DtoClass_ListingOfQualification::new);
	}
}
