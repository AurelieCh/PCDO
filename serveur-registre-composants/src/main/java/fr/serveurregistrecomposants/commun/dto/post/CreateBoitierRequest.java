package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateBoitierRequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String taille;
    private Boolean rgb;
    private Boolean ventilateursInclus;
}
