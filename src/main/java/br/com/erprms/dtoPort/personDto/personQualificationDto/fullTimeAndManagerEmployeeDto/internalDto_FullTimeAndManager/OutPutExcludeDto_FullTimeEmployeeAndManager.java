package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeAndManagerEmployeeDto.internalDto_FullTimeAndManager;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutPutExcludeDto_FullTimeEmployeeAndManager implements PersonQualificationOutputDtoInterface{
	private String specifiedQualification;
	private String personName;
	private Long cpfOrCnpj;
	private String observation;
	
	public OutPutExcludeDto_FullTimeEmployeeAndManager(	
			PersonQualificationSuperclassEntity managerOrEmployeeToDelete,
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.personName = managerOrEmployeeToDelete.getPerson().getFullNameOrEntityName();
		this.cpfOrCnpj = managerOrEmployeeToDelete.getPerson().getCpfCnpj();
		this.observation = managerOrEmployeeToDelete.getObservation();
	}
}

