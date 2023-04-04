package fr.serveurregistrecomposants.commun.dto.put;

import fr.serveurregistrecomposants.commun.Categorie;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PutComposantRequest {
    private Integer id;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    private List<PutCaracteristiqueRequest> caracteristiqueList;
    public boolean contientCaracteristiques(String[] caracs){
        boolean contient = false;
        for(String c : caracs){
            contient = false;
            for(PutCaracteristiqueRequest l : caracteristiqueList){
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
