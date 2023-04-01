package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateAlimResponse {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Double puissance;
    private Double rendement;
}
