package fr.serveurregistrecommandes.commun;

import fr.serveurregistrecommandes.commun.types.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String email;
    private Date dateCommande;
    private String adresse;
    private Status status;
    private Double prix;

    @ElementCollection
    private List<Double> tousPrix;
    @ElementCollection
    private List<Integer> composants;

}
