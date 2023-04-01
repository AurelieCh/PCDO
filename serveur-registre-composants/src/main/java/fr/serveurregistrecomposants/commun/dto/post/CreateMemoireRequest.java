package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateMemoireRequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Double frequence;
    private Integer capacite;
    private String type;
}
