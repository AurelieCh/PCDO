package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCaracteristiqueRequest {
    private String nomCaracteristique;
    private String val;
}
