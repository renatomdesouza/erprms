package br.com.erprms.domainModel.capitalDomain.active;

import br.com.erprms.domainModel.capitalDomain.active.activeType.TypeOfPaymentOrderEnum;
import br.com.erprms.domainModel.personDomain.PersonEntity;
//import br.com.erprms.DomainModel.PessoasDomain.PessJuridicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="paiment_order_receivable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaimentOrderReceivableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeOfPaymentOrderEnum tipoDupicataOuCheque;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity pessFisic;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private PessJuridicEntity pessJuridic;

    private BigDecimal monetaryValue;

    private LocalDate dueDateForPaymet;

    private String observations;
}
