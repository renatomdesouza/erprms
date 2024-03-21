package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecord_RegistryOfManager;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GetterManagerWithDtoService {
	private PersonRepository personRepository;
	private Long person_Id;
	@Getter private PersonEntity person;
	@Getter private BigDecimal salary;
	@Getter private SectorEnum sector;
	@Getter private String observation;
	@Getter private LocalDate initialDate;
	
	public GetterManagerWithDtoService(DtoRecord_RegistryOfManager dtoRecordToManagerRegistry) {
		this(
				dtoRecordToManagerRegistry.person_Id(),
				dtoRecordToManagerRegistry.monthlySalary(),
				dtoRecordToManagerRegistry.sector(),
				dtoRecordToManagerRegistry.observation() );
	}

	private GetterManagerWithDtoService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	private GetterManagerWithDtoService(
				Long person_Id, 
				BigDecimal salary, 
				SectorEnum sector, 
				String observation ) {
		this.person_Id = person_Id;
		this.salary = salary;
		this.sector = sector;
		this.observation = observation;
		this.initialDate = LocalDate.now();
		
		setPerson(this.person_Id);
	}
	
	@Transactional
	@SuppressWarnings("null")
	private void setPerson(Long person_id) {
		new GetterManagerWithDtoService(personRepository);
		
		this.person = personRepository.getReferenceById(person_id);
	}
}
