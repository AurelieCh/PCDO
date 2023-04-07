package fr.serveurregistrecompte.services.dtoCompte;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class GetCompteResponse {
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String password;
    private List<Integer> panier;
    private List<Integer> commandes;
    private List<Integer> facturations;
}
