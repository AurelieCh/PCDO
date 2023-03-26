package fr.serveurregistrecompte.services.dtoPanier;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetComposantsResponse {
    private String image;

    private String nom;

    private String marque;

    private String prix;
}
