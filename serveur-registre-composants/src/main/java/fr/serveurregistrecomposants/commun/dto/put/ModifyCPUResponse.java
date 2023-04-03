package fr.serveurregistrecomposants.commun.dto.put;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModifyCPUResponse {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String socket;
    private Integer nbcoeurs;
    private Double frequence;
}
