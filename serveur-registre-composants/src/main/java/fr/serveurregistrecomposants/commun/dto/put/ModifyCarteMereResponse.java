package fr.serveurregistrecomposants.commun.dto.put;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ModifyCarteMereResponse {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String taille;
    private String socket;
    private Integer nbBarettes;
}
