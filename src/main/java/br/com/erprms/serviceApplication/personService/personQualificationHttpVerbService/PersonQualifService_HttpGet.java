package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_FullTimeEmployeeListing;
import br.com.erprms.repositoryAdapter.personRepository.ManagerRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonQualifService_HttpGet {
	private ManagerRepository managerRepository;
	
	public PersonQualifService_HttpGet(ManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}
	
	@Transactional   
	public Page<DtoClass_FullTimeEmployeeListing> listingService(Pageable qualificationPageable) {  
		return managerRepository
				.findEmployeePersonQualificationByFinalDateIsNull(qualificationPageable)
				.map(DtoClass_FullTimeEmployeeListing::new);
	}
}
