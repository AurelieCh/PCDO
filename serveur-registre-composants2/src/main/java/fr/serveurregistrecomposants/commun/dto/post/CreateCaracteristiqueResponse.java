package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCaracteristiqueResponse {
    private String nomCaracteristique;
    private String val;
}
