package fr.serveurregistrecomposants.commun.dto.post;

import fr.serveurregistrecomposants.commun.Categorie;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateComposantResponse {
    private Integer idComposant;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    private List<CreateCaracteristiqueResponse> caracteristiqueList;
}
