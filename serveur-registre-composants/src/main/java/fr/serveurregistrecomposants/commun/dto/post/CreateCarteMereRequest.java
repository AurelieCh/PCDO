package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateCarteMereRequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String taille;
    private String socket;
    private Integer nbBarrettes;
}
