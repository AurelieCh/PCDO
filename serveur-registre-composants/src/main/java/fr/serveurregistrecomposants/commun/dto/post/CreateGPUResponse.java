package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateGPUResponse {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Integer nbVentilateurs;
    private Double frequence;
}
