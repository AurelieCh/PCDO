package fr.serveurregistrecomposants.commun;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Caracteristique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_caracteristique", nullable = false)
    private Integer idCaracteristique;
    @ManyToOne
    @JoinColumn(name = "composant_id")
    private Composant composant;
    private String nomCaracteristique;
    private String val;
}
