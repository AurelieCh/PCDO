package fr.serveurregistrecomposants.commun.dto.post;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateGPURequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Integer nbventilateurs;
    private Double frequence;
    private Integer vram;
}
