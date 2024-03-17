package br.com.erprms.domainModel.productDomain.logisticsAndInvoice;

import br.com.erprms.domainModel.productDomain.ProductAndServiceEntity;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.SaleOrPurchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="supplier_invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SaleOrPurchase saleOrPurchase;

    private LocalDate requestDate;

    private LocalDate budgetDate;

    private LocalDate effectiveSaleDate;

    private LocalDate fiscalDocumentIssueDate;

    private String fiscalDocumentCode;

    private BigDecimal monetaryValue;

    private LocalDate deliveryDate;

    private LocalDate invoiceDueDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private CollaboratorEntity sellerOrPurchaser;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private ProviderEntity provider;
//
//    @ManyToOne (fetch = FetchType.LAZY)
//    private ClientEntity client;

    @ManyToMany(targetEntity = ProductAndServiceEntity.class, cascade = CascadeType.ALL)
    @JoinTable(name="invoice-x-product",
            joinColumns = { @JoinColumn(name="fatura_id") },
            inverseJoinColumns = { @JoinColumn(name="produto_id") })
    private List<ProductAndServiceEntity> manufacturedProducts = new ArrayList<>();
}