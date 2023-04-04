package fr.serveurregistrecomposants.commun.dto.del;

import fr.serveurregistrecomposants.commun.Categorie;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DelComposantResponse {
    private Integer idComposant;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    private List<DelCaracteristiqueResponse> caracteristiqueList;
}
