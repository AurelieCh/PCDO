package fr.serveurregistrefacturation.commun;

import javax.persistence.*;

import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private Integer id;
    private String email;
    private String adresse;
    private Double prix;
    @ElementCollection
    private List<Double> tousPrix;
    private Date dateCreation;
    private Integer commande;
    private TypePaiement typePaiement;

}
