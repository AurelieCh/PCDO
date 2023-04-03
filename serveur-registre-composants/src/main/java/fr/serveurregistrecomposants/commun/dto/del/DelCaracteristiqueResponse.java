package fr.serveurregistrecomposants.commun.dto.del;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DelCaracteristiqueResponse {
    private String nomCaracteristique;
    private String val;
}
