package br.com.erprms.repositoryAdapter.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.personDomain.personComponent.foneAndAdditionalAddress.AdditionalAddressEntity;

public interface AdditionalAddressRepository extends JpaRepository<AdditionalAddressEntity, Long>{

}
