package br.com.erprms.repositoryAdapter.productRepository.logisticsAndInvoiceRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.productDomain.logisticsAndInvoice.LogisticsOfProductsAndServices;


public interface LogisticaDoProdutoOuServicoRepository extends JpaRepository<LogisticsOfProductsAndServices, Long>{

}
