package br.com.erprms.dtoPort.personDto.personQualificationDto.fullTimeEmployeeDto;

import java.net.URI;

import org.springframework.data.domain.Page;

public record DtoRecord_ManagerAndFullTimeEmployeeOutputPage_With_Uri(
		Page<DtoClass_ManagerAndFullTimeEmployeeToListing> pageableDto,
		URI uri) {

//	public Page<DtoClass_ManagerAndFullTimeEmployeeToListing> pageableDto() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
