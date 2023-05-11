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
public class GetFactureResponse {
    private Integer id;
    private String email;
    private String adresse;
    private Date dateCreation;
    private Double prix;
    private List<Double> tousPrix;
    private Integer commande;
    private TypePaiement typePaiement;
}
