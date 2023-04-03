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
public class GetFactureResponse {
<<<<<<< Updated upstream
    private Long id;
=======
    private Integer id;
>>>>>>> Stashed changes
    private String email;
    private String adresse;
    private Date dateCreation;
    private Integer commande;
    private TypePaiement typePaiement;
}
