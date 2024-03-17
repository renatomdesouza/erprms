package br.com.erprms.domainModel.productDomain.logisticsAndInvoice;

import br.com.erprms.domainModel.productDomain.ProductAndServiceEntity;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.DemandStrategy;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.NumberOfInstallments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="logistics_of_products_and_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsOfProductsAndServices {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private ProductAndServiceEntity productAndService;

    @Enumerated(EnumType.STRING)
    private DemandStrategy demandStrategy;

    @Enumerated(EnumType.STRING)
    private NumberOfInstallments numberOfInstallments;

    private BigDecimal faceValuePurchased;

    private BigDecimal faceValueForNewPurchase;

    private BigDecimal averageSalesValue;

    private BigDecimal minimumAmountForDiscont;

    private Long quantityStock;

    private Long reservedQuantityStock;

    private Long minimumQuantityStock;

    private Long maximumQuantityStock;
}
