package br.com.erprms.repositoryAdapter.capitalRepository.passive;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.capitalDomain.passive.contributionType.ContingenciesOfTheContributionEntity;


public interface ContingenciamentoDoAporteRepository extends JpaRepository<ContingenciesOfTheContributionEntity, Long>{

}
