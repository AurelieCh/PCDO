package fr.serveurregistrefacturation.services.dto;

import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateFactureRequest {
    private String email;
    private Integer commande;
    private String adresse;
    private Double prix;
    private TypePaiement typePaiement;
}
