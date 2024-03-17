package br.com.erprms.domainModel.capitalDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.erprms.domainModel.capitalDomain.active.StoqueAccountCashEntity;
import br.com.erprms.domainModel.capitalDomain.active.activeType.InletOrOutlet;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="financial_movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialMovementEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private InletOrOutlet inletOrOutlet;
	
	private LocalDate movimentData;
	
	private BigDecimal currentValue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private StoqueAccountCashEntity stoqueAccountCashEntityEntity; 
}
