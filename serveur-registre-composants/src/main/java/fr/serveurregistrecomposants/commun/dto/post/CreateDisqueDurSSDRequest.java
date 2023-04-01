package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateDisqueDurSSDRequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Integer capacite;
    private String type;
}
