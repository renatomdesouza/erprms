package br.com.erprms.domainModel.capitalDomain.passive.contributionType;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
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
@Table(name="readjustment_of_indexed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadjustmentOfIndexedEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ContingenciesOfTheContributionEntity contingenciesOfTheContribution;
	
	private LocalDate monthAndYearOfReadjustment;
	
	private BigDecimal newReadjustedValue;
	
	private int realInterestRates;
}
