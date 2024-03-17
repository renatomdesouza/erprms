package br.com.erprms.domainModel.capitalDomain.active;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.erprms.domainModel.capitalDomain.FinancialMovementEntity;
import br.com.erprms.domainModel.capitalDomain.active.activeType.ActiveTypeEnum;

@Entity
@Table(name="stoque_account_cash")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StoqueAccountCashEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActiveTypeEnum activeType;

    private LocalDate openingDate;

    private LocalDate closingDate;

    private BigDecimal initialFaceValue;
    
    @OneToMany(mappedBy = "stoqueAccountCashEntityEntity", cascade = CascadeType.ALL)
    private Set<FinancialMovementEntity>  financialMovementEnties = new HashSet<>();
}
