package fr.serveurregistrecompte.services.dtoCompte;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateCompteRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String password2;
    private String adresse;

}
