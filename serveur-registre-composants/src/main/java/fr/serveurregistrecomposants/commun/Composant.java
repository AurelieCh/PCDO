package fr.serveurregistrecomposants.commun;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Composant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer idComposant;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Caracteristique> caracteristiqueList;
}
