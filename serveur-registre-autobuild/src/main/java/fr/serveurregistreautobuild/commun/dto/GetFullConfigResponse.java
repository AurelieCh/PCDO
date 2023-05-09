package fr.serveurregistreautobuild.commun.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFullConfigResponse {
    private Integer id;
    private GetComposantResponse cpu;
    private GetComposantResponse gpu;
    private GetComposantResponse ssd;
    private GetComposantResponse hdd;
    private GetComposantResponse boitier;
    private GetComposantResponse alim;
    private GetComposantResponse carteMere;
    private GetComposantResponse ram;
}
