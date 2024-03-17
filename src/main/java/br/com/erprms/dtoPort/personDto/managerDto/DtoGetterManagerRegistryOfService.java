package br.com.erprms.dtoPort.personDto.managerDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;
import br.com.erprms.repositoryAdapter.personRepository.PersonRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DtoGetterManagerRegistryOfService {
	
	@Autowired
	private PersonRepository personRepository;
	
	private Long person_Id;
	@Getter private PersonEntity person;
	@Getter private BigDecimal salary;
	@Getter private SectorEnum sector;
	@Getter private String observation;
	@Getter private LocalDate initialDate;
	
	public DtoGetterManagerRegistryOfService(DtoRecordToRegistryOfManager dtoRecordToManagerRegistry) {
		this(
				dtoRecordToManagerRegistry.person_Id(),
				dtoRecordToManagerRegistry.salary(),
				dtoRecordToManagerRegistry.sector(),
				dtoRecordToManagerRegistry.observation() );
	}

	private DtoGetterManagerRegistryOfService(
										Long person_Id, 
										BigDecimal salary, 
										SectorEnum sector, 
										String observation ) {
		this.person_Id = person_Id;
//		this.person = personRepository.getReferenceById(person_Id);
		this.salary = salary;
		this.sector = sector;
		this.observation = observation;
		this.initialDate = LocalDate.now();

		setPerson(person_Id);
	}
	
	
	
	private void setPerson(Long Person_id) {
		this.person = personRepository.getReferenceById(person_Id);
	}
	
}
