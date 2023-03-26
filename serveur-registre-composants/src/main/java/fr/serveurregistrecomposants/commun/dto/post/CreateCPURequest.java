package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateCPURequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private String socket;
    private Integer nbcoeurs;
    private Double frequence;
}
