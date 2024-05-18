package br.com.erprms.domainModel.personDomain;

import br.com.erprms.domainModel.personDomain.personComponent.personEnum.HttpVerbEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "person_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonsManagement_Entity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_user")
    private String loginUser;

    @Column(name = "http_verb")
    @Enumerated(EnumType.STRING)
    private HttpVerbEnum httpVerb;

    @Column(name = "initial_date")
    private LocalDateTime initialDate;

    @JoinColumn(name = "person_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity person;

}
