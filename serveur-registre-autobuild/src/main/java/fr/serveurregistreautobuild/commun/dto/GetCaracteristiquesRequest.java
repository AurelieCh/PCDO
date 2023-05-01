package fr.serveurregistreautobuild.commun.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetCaracteristiquesRequest {
    private String nomCaracteristique;
    private String operateur;
    private String val;
}
