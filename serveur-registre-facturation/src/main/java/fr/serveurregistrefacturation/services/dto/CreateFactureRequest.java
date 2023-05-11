package fr.serveurregistrefacturation.services.dto;

import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import java.util.List;

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
    private List<Double> tousPrix;
    private Double prix;
    private TypePaiement typePaiement;
}
