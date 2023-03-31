package fr.serveurregistrecomposants.commun.dto.get;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetGPUResponse {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Integer nbventilateurs;
    private Double frequence;
    private Integer vram;
}
