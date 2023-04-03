package fr.serveurregistrecommandes.services.dto;

import fr.serveurregistrecommandes.commun.types.Status;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateCommandeResponse {
    private Integer id;
    private List<Integer> composants;
    private Double prix;
    private Date dateCommande;
    private String adresse;
    private Status status;
}
