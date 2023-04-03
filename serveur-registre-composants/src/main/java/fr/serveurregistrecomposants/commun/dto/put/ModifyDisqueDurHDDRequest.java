package fr.serveurregistrecomposants.commun.dto.put;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ModifyDisqueDurHDDRequest {
    private Integer idComposant;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Integer capacite;
    private Integer vitesse;
}
