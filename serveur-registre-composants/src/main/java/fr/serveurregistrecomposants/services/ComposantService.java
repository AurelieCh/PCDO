package fr.serveurregistrecomposants.services;

import fr.serveurregistrecomposants.commun.Caracteristique;
import fr.serveurregistrecomposants.commun.Composant;
import fr.serveurregistrecomposants.commun.dto.del.DelCaracteristiqueResponse;
import fr.serveurregistrecomposants.commun.dto.del.DelComposantResponse;
import fr.serveurregistrecomposants.commun.dto.get.*;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueRequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateCaracteristiqueResponse;
import fr.serveurregistrecomposants.commun.dto.post.CreateComposantRequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateComposantResponse;
import fr.serveurregistrecomposants.commun.dto.put.PutCaracteristiqueRequest;
import fr.serveurregistrecomposants.commun.dto.put.PutCaracteristiqueResponse;
import fr.serveurregistrecomposants.commun.dto.put.PutComposantRequest;
import fr.serveurregistrecomposants.commun.dto.put.PutComposantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.serveurregistrecomposants.repository.CaracteristiqueRepository;
import fr.serveurregistrecomposants.repository.ComposantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComposantService {
    @Autowired
    private ComposantRepository repCompo;
    @Autowired
    private CaracteristiqueRepository repCarac;

    ////////////////////////////////////// POST ///////////////////////////////////
    public CreateComposantResponse saveComposant(CreateComposantRequest request) {
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

    private Caracteristique buildCaracteristique(CreateCaracteristiqueRequest request, Composant c) {
        return Caracteristique.builder()
                .nomCaracteristique(request.getNomCaracteristique())
                .val(request.getVal())
                .composant(c)
                .build();
    }

    private CreateComposantResponse buildCreateComposantResponse(Composant c) {
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

    private CreateCaracteristiqueResponse buildCreateCaracteristiqueResponse(Caracteristique c, Integer idComposant) {
        return CreateCaracteristiqueResponse.builder()
                .nomCaracteristique(c.getNomCaracteristique())
                .val(c.getVal())
                .build();
    }

    //////////////////////////////////////// GET ///////////////////////////////////

    private GetComposantResponse buildGetComposantResponse(Composant c) {
        return GetComposantResponse.builder()
                .marque(c.getMarque())
                .nom(c.getNom())
                .prix(c.getPrix())
                .url(c.getUrl())
                .categorie(c.getCategorie())
                .idComposant(c.getIdComposant())
                .description(c.getDescription())
                .caracteristiqueList(c.getCaracteristiqueList().stream().map(this::buildGetCaracteristiqueResponse).collect(Collectors.toList()))
                .build();
    }

    private GetCaracteristiqueResponse buildGetCaracteristiqueResponse(Caracteristique c) {
        return GetCaracteristiqueResponse.builder()
                .val(c.getVal())
                .nomCaracteristique(c.getNomCaracteristique())
                .build();
    }

    public GetComposantResponse getComposant(Integer id) {
        Optional<Composant> temp = this.repCompo.findById(id);
        return temp.map(this::buildGetComposantResponse).orElse(null);
    }

    private GetComposantListResponse buildGetComposantListResponse(List<Composant> liste) {
        GetComposantListResponse toReturn = new GetComposantListResponse();
        toReturn.setComposants(new ArrayList<>());
        toReturn.getComposants().addAll(liste.stream().map(this::buildGetComposantResponse).toList());
        return toReturn;
    }

    public GetComposantListResponse getComposant(GetComposantRequest request) {
        GetComposantListResponse toReturn = null;
        if (request == null) {
            return GetComposantListResponse.builder()
                    .composants(this.repCompo.findAll().stream().map(this::buildGetComposantResponse).collect(Collectors.toList()))
                    .build();
        } else {
            List<Composant> liste = this.repCompo.findAll();
            if (request.getPrixMin() != null)
                liste = liste.stream().filter(temp -> temp.getPrix() >= request.getPrixMin()).collect(Collectors.toList());
            if (request.getPrixMax() != null)
                liste = liste.stream().filter(temp -> temp.getPrix() <= request.getPrixMax()).collect(Collectors.toList());
            if (request.getNom() != null)
                liste = liste.stream().filter(temp -> temp.getNom().contains(request.getNom())).collect(Collectors.toList());
            if (request.getMarque() != null)
                liste = liste.stream().filter(temp -> temp.getMarque().contains(request.getMarque())).collect(Collectors.toList());
            if (request.getDescription() != null)
                liste = liste.stream().filter(temp -> temp.getDescription().contains(request.getDescription())).collect(Collectors.toList());
            if (request.getCategorie() != null)
                liste = liste.stream().filter(temp -> temp.getCategorie().equals(request.getCategorie())).collect(Collectors.toList());
            if (request.getCaracteristiqueList() != null) {
                for (GetCaracteristiquesRequest carac : request.getCaracteristiqueList()) {
                    if (carac.getOperateur() != null) {
                        liste = switch (carac.getOperateur().toLowerCase()) {
                            case ">" -> liste.stream().filter(temp -> temp.getCaracteristiqueList().stream()
                                    .anyMatch(temp2 -> temp2.getNomCaracteristique().equals(carac.getNomCaracteristique()) && Double.parseDouble(temp2.getVal()) > Double.parseDouble(carac.getVal()))).collect(Collectors.toList());
                            case "<" -> liste.stream().filter(temp -> temp.getCaracteristiqueList().stream()
                                    .anyMatch(temp2 -> temp2.getNomCaracteristique().equals(carac.getNomCaracteristique()) && Double.parseDouble(temp2.getVal()) < Double.parseDouble(carac.getVal()))).collect(Collectors.toList());
                            case ">=" -> liste.stream().filter(temp -> temp.getCaracteristiqueList().stream()
                                    .anyMatch(temp2 -> temp2.getNomCaracteristique().equals(carac.getNomCaracteristique()) && Double.parseDouble(temp2.getVal()) >= Double.parseDouble(carac.getVal()))).collect(Collectors.toList());
                            case "<=" -> liste.stream().filter(temp -> temp.getCaracteristiqueList().stream()
                                    .anyMatch(temp2 -> temp2.getNomCaracteristique().equals(carac.getNomCaracteristique()) && Double.parseDouble(temp2.getVal()) <= Double.parseDouble(carac.getVal()))).collect(Collectors.toList());
                            case "=" -> liste.stream().filter(temp -> temp.getCaracteristiqueList().stream()
                                    .anyMatch(temp2 -> temp2.getNomCaracteristique().equals(carac.getNomCaracteristique()) && temp2.getVal().equals(carac.getVal()))).collect(Collectors.toList());
                            case "!=" -> liste.stream().filter(temp -> temp.getCaracteristiqueList().stream()
                                    .anyMatch(temp2 -> temp2.getNomCaracteristique().equals(carac.getNomCaracteristique()) && !temp2.getVal().equals(carac.getVal()))).collect(Collectors.toList());
                            default -> liste;
                        };
                    }
                }
            }
            toReturn = buildGetComposantListResponse(liste); // si nul, throw no content
        }
        return toReturn;
    }

    ////////////////////////////////////////////// PUT ///////////////////////////////////////////////

    private Caracteristique buildCaracteristique(PutCaracteristiqueRequest request, Composant c) {
        return Caracteristique.builder()
                .nomCaracteristique(request.getNomCaracteristique())
                .val(request.getVal())
                .composant(c)
                .build();
    }

    private PutCaracteristiqueResponse buildPutCaracteristiqueResponse(Caracteristique c, Integer idComposant) {
        return PutCaracteristiqueResponse.builder()
                .nomCaracteristique(c.getNomCaracteristique())
                .val(c.getVal())
                .build();
    }


    private PutComposantResponse buildPutComposantResponse(Composant c) {
        return PutComposantResponse.builder()
                .marque(c.getMarque())
                .nom(c.getNom())
                .categorie(c.getCategorie())
                .description(c.getDescription())
                .url(c.getUrl())
                .prix(c.getPrix())
                .idComposant(c.getIdComposant())
                .caracteristiqueList(c.getCaracteristiqueList().stream().map(temp -> buildPutCaracteristiqueResponse(temp, c.getIdComposant())).collect(Collectors.toList()))
                .build();
    }

    public PutComposantResponse modifyComposant(PutComposantRequest request) {
        Optional<Composant> optTemp = this.repCompo.findById(request.getId());
        if (optTemp.isEmpty()) {
            return new PutComposantResponse(); //throw not found
        }

        Composant c = optTemp.get();
        List<Integer> caracObsoletes = c.getCaracteristiqueList().stream().map(Caracteristique::getIdCaracteristique).toList();

        c.setPrix(request.getPrix());
        c.setNom(request.getNom());
        c.setMarque(request.getMarque());
        c.setUrl(request.getUrl());
        c.setDescription(request.getDescription());
        c.setCaracteristiqueList(request.getCaracteristiqueList().stream().map(temp -> buildCaracteristique(temp, c)).collect(Collectors.toList()));

        caracObsoletes.forEach(x -> this.repCarac.deleteById(x));
        return buildPutComposantResponse(this.repCompo.save(c));
    }

    //////////////////////////////////////////// DEL ////////////////////////////////////////
    public DelComposantResponse deleteComposant(Integer id) throws Exception {
        Optional<Composant> temp = this.repCompo.findById(id);
        if (temp.isEmpty()){
            throw new Exception("Not Found");
        }
        Composant c = temp.get();
        List<Integer> caracObsoletes = c.getCaracteristiqueList().stream().map(Caracteristique::getIdCaracteristique).toList();


        caracObsoletes.forEach(x -> this.repCarac.deleteById(x));
        this.repCompo.deleteById(id);
        return buildDelComposantResponse(c);
    }

    private DelComposantResponse buildDelComposantResponse(Composant c) {
        return DelComposantResponse.builder()
                .idComposant(c.getIdComposant())
                .prix(c.getPrix())
                .nom(c.getNom())
                .marque(c.getMarque())
                .description(c.getDescription())
                .url(c.getUrl())
                .categorie(c.getCategorie())
                .caracteristiqueList(c.getCaracteristiqueList().stream().map(this::buildDelCaracteristiqueResponse).collect(Collectors.toList()))
                .build();
    }

    private DelCaracteristiqueResponse buildDelCaracteristiqueResponse(Caracteristique c){
        return DelCaracteristiqueResponse.builder()
                .nomCaracteristique(c.getNomCaracteristique())
                .val(c.getVal())
                .build();
    }
}
