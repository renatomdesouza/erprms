package br.com.erprms.serviceApplication.personService;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.dtoPort.personDto.managerDto.DtoRecordToRegistryOfManager;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GetterManagerService {
	private PersonRepository personRepository;
	private Long person_Id;
	@Getter private PersonEntity person;
	@Getter private BigDecimal salary;
	@Getter private SectorEnum sector;
	@Getter private String observation;
	@Getter private LocalDate initialDate;
	
	public GetterManagerService(DtoRecordToRegistryOfManager dtoRecordToManagerRegistry) {
		this(
				dtoRecordToManagerRegistry.person_Id(),
				dtoRecordToManagerRegistry.monthlySalary(),
				dtoRecordToManagerRegistry.sector(),
				dtoRecordToManagerRegistry.observation() );
	}

	private GetterManagerService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	private GetterManagerService(
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
		new GetterManagerService(personRepository);
		
		this.person = personRepository.getReferenceById(person_id);
	}
}
