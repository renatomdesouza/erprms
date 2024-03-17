package br.com.erprms.repositoryAdapter.capitalRepository.passive;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.capitalDomain.passive.FutureFinancialReserveEntity;


public interface AporteFuturoFinanceiroRepository extends JpaRepository<FutureFinancialReserveEntity, Long>{

}
