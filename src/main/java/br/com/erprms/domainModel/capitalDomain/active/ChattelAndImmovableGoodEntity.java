package br.com.erprms.domainModel.capitalDomain.active;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.erprms.domainModel.capitalDomain.active.activeType.ChattelAndImmovableGoodEnum;

@Entity
@Table(name = "chattels_and_immovables_goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChattelAndImmovableGoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChattelAndImmovableGoodEnum chattelOrImmovableGood;

    private BigDecimal estimatedValue;

    private LocalDate estimatedValueDate;

    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private ProviderEntity provider;
}
