package br.com.erprms.dtoPort.personDto.personQualificationDto.partTimeEmployeeDto.internalDto_PartTimeEmployee;

import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputDtoClassExclude_PartTimeEmployee implements PersonQualificationOutputDtoInterface{
	private String specifiedQualification;
	private String personName;
	private Long cpfOrCnpj;
	private String observation;
	
	public OutputDtoClassExclude_PartTimeEmployee(	
			PersonQualificationSuperclassEntity partyTimeEmployeeToDelete,
			String specifiedQualification) {
		this.specifiedQualification = specifiedQualification;
		this.personName = partyTimeEmployeeToDelete.getPerson().getFullNameOrEntityName();
		this.cpfOrCnpj = partyTimeEmployeeToDelete.getPerson().getCpfOrCnpj();
		this.observation = partyTimeEmployeeToDelete.getObservation();
	}
}
