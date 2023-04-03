package fr.serveurregistrecomposants.commun.dto.put;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModifyGPUResponse {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Integer nbVentilateurs;
    private Double frequence;
    private Integer vram;
}
