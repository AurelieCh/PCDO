package fr.serveurregistreautobuild.services;

import fr.serveurregistreautobuild.commun.Configuration;
import fr.serveurregistreautobuild.commun.dto.*;
import fr.serveurregistreautobuild.commun.exceptions.ServiceUnavailable;
import fr.serveurregistreautobuild.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {
    @Autowired
    private ConfigurationRepository repConfig;

    @Autowired
    private RestTemplate rest;

    public GetConfigResponse randomConfig() throws Exception {
        Random r = new Random();
        ArrayList<GetComposantListResponse> composants = new ArrayList<>();
        // Récupération de la liste des composants
        for(Categorie c : Categorie.values()){
            GetComposantRequest requestBody = GetComposantRequest.builder().categorie(c).build();
            ResponseEntity<GetComposantListResponse> req = rest.postForEntity("http://ms-composants/composants/search", requestBody, GetComposantListResponse.class);
            if (req.getStatusCode() != HttpStatus.OK && req.getStatusCode() != HttpStatus.NO_CONTENT) throw new ServiceUnavailable("Le service Composants est down.");
            GetComposantListResponse all = req.getBody();
            if (all.getComposants() == null) throw new Exception("Pas de composants");
            composants.add(all);
        }
        //A ce stade, la liste des composants par catégorie a été récupérée et est stockée dans composants.

        List<GetComposantResponse> config = null;
        do {
            config = new ArrayList<>();
            for(GetComposantListResponse compo : composants){
                config.add(compo.getComposants().get(r.nextInt(compo.getComposants().size())));
            }
        } while (!checkConfig(config));

        return buildGetConfigResponse(buildConfiguration(config));
    }

    public boolean checkConfig(List<GetComposantResponse> config){
        try {
            if (config.size() != Categorie.values().length) {
                return false;
            }
            for (Categorie c : Categorie.values()) {
                if (config.stream().noneMatch(t -> t.getCategorie() == c)) {
                    return false;
                }
            }

            Optional<GetComposantResponse> opt = config.stream().filter(t-> t.getCategorie() == Categorie.Processeur).findFirst();
            if (opt.isEmpty()) throw new Exception();
            GetComposantResponse cpu = opt.get();

            opt = config.stream().filter(t-> t.getCategorie() == Categorie.CarteMere).findFirst();
            if (opt.isEmpty()) throw new Exception();
            GetComposantResponse carteMere = opt.get();

            opt = config.stream().filter(t-> t.getCategorie() == Categorie.Boitier).findFirst();
            if (opt.isEmpty()) throw new Exception();
            GetComposantResponse boitier = opt.get();

            if (!Objects.equals(cpu.getCaracteristiqueList().stream().filter(t -> Objects.equals(t.getNomCaracteristique(), "socket")).findFirst().get().getVal(),
                    carteMere.getCaracteristiqueList().stream().filter(x -> Objects.equals(x.getNomCaracteristique(), "socket")).findFirst().get().getVal())) {
                return false;
            }

            if (!Objects.equals(carteMere.getCaracteristiqueList().stream().filter(t -> Objects.equals(t.getNomCaracteristique(), "taille")).findFirst().get().getVal(),
                    boitier.getCaracteristiqueList().stream().filter(x -> Objects.equals(x.getNomCaracteristique(), "taille")).findFirst().get().getVal())) {
                return false;
            }

            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Configuration buildConfiguration(List<GetComposantResponse> temp) throws Exception {
        if (!checkConfig(temp)) throw new Exception();
        // La configuration est estimée valide ; il n'est donc pas nécessaire de vérifier les Optional ci-dessous.
        return Configuration.builder()
                .cpu(temp.stream().filter(t -> t.getCategorie() == Categorie.Processeur).findFirst().get().getIdComposant())
                .gpu(temp.stream().filter(t -> t.getCategorie() == Categorie.CarteGraphique).findFirst().get().getIdComposant())
                .hdd(temp.stream().filter(t -> t.getCategorie() == Categorie.DisqueDurHDD).findFirst().get().getIdComposant())
                .ssd(temp.stream().filter(t -> t.getCategorie() == Categorie.DisqueDurSSD).findFirst().get().getIdComposant())
                .ram(temp.stream().filter(t -> t.getCategorie() == Categorie.MemoireRAM).findFirst().get().getIdComposant())
                .alim(temp.stream().filter(t -> t.getCategorie() == Categorie.Alimentation).findFirst().get().getIdComposant())
                .boitier(temp.stream().filter(t -> t.getCategorie() == Categorie.Boitier).findFirst().get().getIdComposant())
                .carteMere(temp.stream().filter(t -> t.getCategorie() == Categorie.CarteMere).findFirst().get().getIdComposant())
                .id(-1)
                .build();
    }

    public GetConfigResponse buildGetConfigResponse(Configuration config){
        return GetConfigResponse.builder()
                .cpu(config.getCpu())
                .gpu(config.getGpu())
                .ram(config.getRam())
                .ssd(config.getSsd())
                .hdd(config.getHdd())
                .alim(config.getAlim())
                .boitier(config.getBoitier())
                .carteMere(config.getCarteMere())
                .build();
    }
}
