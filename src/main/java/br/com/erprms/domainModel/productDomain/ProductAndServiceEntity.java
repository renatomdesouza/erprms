package br.com.erprms.domainModel.productDomain;

import br.com.erprms.domainModel.productDomain.classificationAndUnit.ClassificationOfServiceProvisionEnum;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.ProductClassEnum;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.ProductMeasurementUnitEnum;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.ProductOrService;
import br.com.erprms.domainModel.productDomain.classificationAndUnit.ServiceMeasurementUnitEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_and_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndServiceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductOrService productOrService;

    @Enumerated(EnumType.STRING)
    private ProductClassEnum productClass;

    @Enumerated(EnumType.STRING)
    private ClassificationOfServiceProvisionEnum classificationOfServiceProvision;

    @Enumerated(EnumType.STRING)
    private ProductMeasurementUnitEnum productMeasurementUnit;

    @Enumerated(EnumType.STRING)
    private ServiceMeasurementUnitEnum serviceMeasurementUnitEnum;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private ProviderEntity provider;

    private String description;

    private String manufacturingCode;

    private String manufacturerName;

    private String tradmark;
}
