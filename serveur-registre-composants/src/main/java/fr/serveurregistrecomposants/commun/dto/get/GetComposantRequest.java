package fr.serveurregistrecomposants.commun.dto.get;

import fr.serveurregistrecomposants.commun.Categorie;

import java.util.List;

public class GetComposantRequest {
    private Integer idComposant;
    private Double prix;
    private String nom;
    private String marque;
    private String description;
    private String url;
    private Categorie categorie;
    private List<GetCaracteristiquesRequest> caracteristiqueList;
}
