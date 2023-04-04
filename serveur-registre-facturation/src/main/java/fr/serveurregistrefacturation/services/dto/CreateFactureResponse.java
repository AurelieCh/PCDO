package fr.serveurregistrefacturation.services.dto;

import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import java.util.Date;

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
    private Date dateCreation;
    private TypePaiement typePaiement;
}
