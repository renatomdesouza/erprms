package br.com.erprms.serviceApplication.personService.personQualificationService;

import org.springframework.stereotype.Service;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistry;
import br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto.DtoClass_ManagerAndFullTimeEmployeeRegistryOutput;

@Service
public class PersonQualification_CreateManagerAndEmployeeDto {
	
	public DtoClass_ManagerAndFullTimeEmployeeRegistryOutput createManagerAndEmployeeDto(
				PersonEntity person,
				DtoClass_ManagerAndFullTimeEmployeeRegistry fullTimeEmployeeClassDto,
				String specifiedQualification) {
		return	new DtoClass_ManagerAndFullTimeEmployeeRegistryOutput( 	person, 
																		fullTimeEmployeeClassDto, 
																		specifiedQualification);
	}
	
	
	public DtoClass_ManagerAndFullTimeEmployeeRegistryOutput createManagerAndEmployeeDto(
					PersonQualificationSuperclassEntity managerToDelete,
					String specifiedQualification) {
		return new DtoClass_ManagerAndFullTimeEmployeeRegistryOutput(	managerToDelete, 
																		specifiedQualification);
	}
}

