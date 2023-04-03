package fr.serveurregistrecomposants.commun.dto.post;

import fr.serveurregistrecomposants.commun.Categorie;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateComposantRequest {
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    private List<CreateCaracteristiqueRequest> caracteristiqueList;

    public boolean contientCaracteristiques(String[] caracs){
        boolean contient = false;
        for(String c : caracs){
            contient = false;
            for(CreateCaracteristiqueRequest l : caracteristiqueList){
                if (l.getNomCaracteristique().equals(c)){
                    contient = true;
                    break;
                }
            }
            if (!contient){
                return false;
            }
        }
        return true;
    }
}
