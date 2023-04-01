package fr.serveurregistrecompte.services.dtoCompte;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateCompteResponse {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date dateInscription;
    private String adresse;
    private List<String> panier;
    private List<Integer> commandes;
    private List<Integer> facturations;
}
