package br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity;

import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.SectorEnum;

public interface EmployeePersonQualificatorInterface {
	SectorEnum getSector();
	void setSector(SectorEnum sectorEnum);
}
