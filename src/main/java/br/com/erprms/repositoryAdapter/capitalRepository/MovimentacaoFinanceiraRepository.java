package br.com.erprms.repositoryAdapter.capitalRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.capitalDomain.FinancialMovementEntity;


public interface MovimentacaoFinanceiraRepository extends JpaRepository<FinancialMovementEntity, Long>{

}
