package fr.serveurregistrecommandes.services.dto.composants;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetCaracteristiqueResponse {
    private String nomCaracteristique;
    private String val;
}
