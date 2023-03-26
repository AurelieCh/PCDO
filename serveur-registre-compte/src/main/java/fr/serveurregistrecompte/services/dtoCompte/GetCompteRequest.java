package fr.serveurregistrecompte.services.dtoCompte;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetCompteRequest {
    private String email;
    private String password;
}
