package fr.serveurregistrecommandes.services.dto;

import fr.serveurregistrecommandes.commun.types.Status;
import lombok.*;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetCommandeResponse {
    private Integer id;
    private String email;
    private Date dateCommande;
    private String adresse;
    private Status status;
    private Double prix;
    private List<Integer> composants;
}
