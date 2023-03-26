package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateCPUResponse {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String socket;
    private Integer nbcoeurs;
    private Double frequence;
}
