package br.com.erprms.domainModel.capitalDomain.passive.contributionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.erprms.domainModel.capitalDomain.passive.FutureFinancialReserveEntity;
import br.com.erprms.domainModel.capitalDomain.passive.passiveType.TypeOfInstallmentEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contingencies_of_the_contribution")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContingenciesOfTheContributionEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private FutureFinancialReserveEntity futureFinancialReserveEntity;
	
	@Enumerated(EnumType.STRING)
	private TypeOfInstallmentEnum typeOfInstallment;
	
	private BigDecimal initialValue;
	
	private BigDecimal finalValue;
	
	private LocalDate dateOfTheFirstInstallment;
	
	private LocalDate monthAndYearOfTheFirstInstallment;
	
	private int numberOfInstallments;
	
	@OneToMany(mappedBy = "contingenciesOfTheContribution", cascade = CascadeType.ALL)
	private Set<ReadjustmentOfIndexedEntity> readjustmentsOfIndexed = new HashSet<>();
	
	private BigDecimal propertyValue;
	
	private String description;
	
	private BigDecimal collateralValue;
	
	private BigDecimal estimatedHazardRate;
}
