package fr.serveurregistrecompte.services.dtoCompte;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ModifyCompteRequest {
    private String email;
    private String nom;
    private String prenom;
    private String adresse;
    private String password;
}
