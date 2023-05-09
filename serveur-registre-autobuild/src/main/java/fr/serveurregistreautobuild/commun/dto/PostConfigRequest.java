package fr.serveurregistreautobuild.commun.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostConfigRequest {
    private Integer cpu;
    private Integer gpu;
    private Integer ssd;
    private Integer hdd;
    private Integer boitier;
    private Integer alim;
    private Integer carteMere;
    private Integer ram;
}
