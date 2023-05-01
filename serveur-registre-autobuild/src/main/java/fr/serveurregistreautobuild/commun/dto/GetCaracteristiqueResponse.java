package fr.serveurregistreautobuild.commun.dto;

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
