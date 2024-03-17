package br.com.erprms.repositoryAdapter.productRepository.logisticsAndInvoiceRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erprms.domainModel.productDomain.ProductAndServiceEntity;


public interface ProdutosRepository extends JpaRepository<ProductAndServiceEntity, Long>{

}
