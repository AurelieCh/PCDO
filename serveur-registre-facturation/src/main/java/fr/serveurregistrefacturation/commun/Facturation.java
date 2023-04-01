package fr.serveurregistrefacturation.commun;

import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Facturation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String adresse;
    private Date dateCreation;
    private Integer commande;
    private TypePaiement typePaiement;
}
