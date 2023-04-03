package fr.serveurregistrecomposants.services;

import fr.serveurregistrecomposants.commun.Caracteristique;
import fr.serveurregistrecomposants.commun.Composant;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueRequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueResponse;
import fr.serveurregistrecomposants.commun.dto.post.CreateComposantRequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateComposantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.serveurregistrecomposants.repository.CaracteristiqueRepository;
import fr.serveurregistrecomposants.repository.ComposantRepository;

import java.util.stream.Collectors;

@Service
public class ComposantService {
    @Autowired
    private ComposantRepository repCompo;
    @Autowired
    private CaracteristiqueRepository repCarac;

    public CreateComposantResponse saveComposant(CreateComposantRequest request){
        Composant c = Composant.builder()
                .nom(request.getNom())
                .url(request.getUrl())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .categorie(request.getCategorie())
                .build();
        c = this.repCompo.save(c);
        Composant finalC = c;
        c.setCaracteristiqueList(request.getCaracteristiqueList().stream().map(temp -> buildCaracteristique(temp, finalC)).collect(Collectors.toList()));
        return buildCreateComposantResponse(this.repCompo.save(c));
    }

    private Caracteristique buildCaracteristique(CreateCaracteristiqueRequest request, Composant c){
        return Caracteristique.builder()
                .nomCaracteristique(request.getNomCaracteristique())
                .val(request.getVal())
                .composant(c)
                .build();
    }

    private CreateComposantResponse buildCreateComposantResponse(Composant c){
        return CreateComposantResponse.builder()
                .marque(c.getMarque())
                .nom(c.getNom())
                .categorie(c.getCategorie())
                .description(c.getDescription())
                .url(c.getUrl())
                .prix(c.getPrix())
                .idComposant(c.getIdComposant())
                .caracteristiqueList(c.getCaracteristiqueList().stream().map(temp -> buildCreateCaracteristiqueResponse(temp, c.getIdComposant())).collect(Collectors.toList()))
                .build();
    }

    private CreateCaracteristiqueResponse buildCreateCaracteristiqueResponse(Caracteristique c, Integer idComposant){
        return CreateCaracteristiqueResponse.builder()
                .nomCaracteristique(c.getNomCaracteristique())
                .val(c.getVal())
                .build();
    }
}
