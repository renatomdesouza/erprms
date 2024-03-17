package br.com.erprms.repositoryAdapter.productRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.productDomain.logisticsAndInvoice.InvoiceEntity;


public interface FaturaRepository extends JpaRepository<InvoiceEntity, Long>{

}
