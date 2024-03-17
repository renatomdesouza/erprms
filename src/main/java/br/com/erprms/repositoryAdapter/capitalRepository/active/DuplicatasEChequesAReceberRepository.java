package br.com.erprms.repositoryAdapter.capitalRepository.active;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.capitalDomain.active.PaimentOrderReceivableEntity;


public interface DuplicatasEChequesAReceberRepository extends JpaRepository<PaimentOrderReceivableEntity, Long>{

}
