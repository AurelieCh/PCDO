package fr.serveurregistrecomposants.commun.dto.put;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PutCaracteristiqueRequest {
    private String nomCaracteristique;
    private String val;
}
