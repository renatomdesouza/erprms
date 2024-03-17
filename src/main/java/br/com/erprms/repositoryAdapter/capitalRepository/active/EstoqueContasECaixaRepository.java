package br.com.erprms.repositoryAdapter.capitalRepository.active;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.capitalDomain.active.StoqueAccountCashEntity;


public interface EstoqueContasECaixaRepository extends JpaRepository<StoqueAccountCashEntity, Long>{

}
