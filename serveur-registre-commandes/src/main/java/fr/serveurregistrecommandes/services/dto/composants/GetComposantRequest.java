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
public class GetComposantRequest {
     private Double prixMin;
    private Double prixMax;
    private String nom;
    private String marque;
    private String description;
    private Categorie categorie;
    private List<GetCaracteristiquesRequest> caracteristiqueList;
}
