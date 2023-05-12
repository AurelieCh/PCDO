package fr.serveurregistrecomposants.commun.dto.get;

import fr.serveurregistrecomposants.commun.Categorie;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueResponse;
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
