package fr.serveurregistrecomposants.commun.dto.get;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetBoitierResponse {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String taille;
    private Boolean rgb;
    private Boolean ventilateursInclus;
}
