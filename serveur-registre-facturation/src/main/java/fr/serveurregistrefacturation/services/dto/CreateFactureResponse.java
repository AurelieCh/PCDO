package fr.serveurregistrefacturation.services.dto;

import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateFactureResponse {
    private String email;
    private Integer commande;
    private String adresse;
    private Double prix;
    private List<Double> tousPrix;
    private Date dateCreation;
    private TypePaiement typePaiement;
}
