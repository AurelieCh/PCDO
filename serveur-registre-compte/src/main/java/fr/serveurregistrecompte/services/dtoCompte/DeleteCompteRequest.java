package fr.serveurregistrecompte.services.dtoCompte;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeleteCompteRequest {
    private String email;
    private String password;
}
