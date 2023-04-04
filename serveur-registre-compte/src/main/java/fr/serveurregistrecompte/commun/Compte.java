package fr.serveurregistrecompte.commun;

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
public class Compte {
    @Id
    @Column(name = "email", nullable = false)
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String adresse;
    //List d'ids afin de récupérer les composants et de les afficher dans le panier
    @ElementCollection
    private List<String> panier;
    @ElementCollection
    private List<Integer> commandes;
    @ElementCollection
    private List<Integer> facturations;
    private Date dateInscription;

}
