package br.com.erprms.domainModel.personDomain.personQualification;

import java.time.LocalDate;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_qualification")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "specifiedQualification", discriminatorType = DiscriminatorType.STRING)
public class PersonQualificationSuperclassEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "professional_registry")
	private String professionalRegistry;
	
	@Column(name = "initial_date")
	private LocalDate initialDate;
	
	@Column(name = "final_date")
	private LocalDate finalDate;
	
	@Column(name = "observation")
	private String observation;
	
	@JoinColumn(name = "person_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private PersonEntity person;
}
