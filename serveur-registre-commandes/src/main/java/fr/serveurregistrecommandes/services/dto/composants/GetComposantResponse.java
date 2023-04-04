package fr.serveurregistrecommandes.services.dto.composants;

import fr.serveurregistrecommandes.commun.types.Categorie;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetComposantResponse {
    private Integer idComposant;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    private List<GetCaracteristiqueResponse> caracteristiqueList;
}
