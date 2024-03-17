package br.com.erprms.domainModel.capitalDomain.passive;

import java.util.HashSet;
import java.util.Set;

import br.com.erprms.domainModel.capitalDomain.passive.contributionType.ContingenciesOfTheContributionEntity;
import br.com.erprms.domainModel.capitalDomain.passive.passiveType.ReserveTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="future_financial_reserve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FutureFinancialReserveEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ReserveTypeEnum reserveType;
	
	private String observation;
	
	@OneToMany(mappedBy = "futureFinancialReserveEntity", cascade = CascadeType.ALL)
	private Set<ContingenciesOfTheContributionEntity> contingenciamentosDosAportes = new HashSet<>(); 
}
