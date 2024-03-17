package br.com.erprms.domainModel.personDomain.personComponent.foneAndAdditionalAddress;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="fone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoneEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fone_number")
    private String foneNumber;
    
    @Column(name = "is_whatsapp")
    private boolean isWhatsapp;
    
    @JoinColumn(name = "person_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private PersonEntity person;
}
