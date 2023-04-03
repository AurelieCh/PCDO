package fr.serveurregistrecomposants.services;

import fr.serveurregistrecomposants.commun.*;
import fr.serveurregistrecomposants.commun.dto.get.*;
import fr.serveurregistrecomposants.commun.dto.post.*;
import fr.serveurregistrecomposants.commun.dto.put.ModifyCPURequest;
import fr.serveurregistrecomposants.commun.dto.put.ModifyCPUResponse;
import fr.serveurregistrecomposants.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComposantService {
    @Autowired
    private ComposantRepository repCompo;
    @Autowired
    private ProcesseurRepository repProc;
    @Autowired
    private CarteGraphiqueRepository repGraph;
    @Autowired
    private AlimentationRepository repAlim;
    @Autowired
    private BoitierRepository repBoitier;
    @Autowired
    private CarteMereRepository repCarteMere;
    @Autowired
    private DisqueDurHDDRepository repHDD;
    @Autowired
    private DisqueDurSSDRepository repSSD;
    @Autowired
    private MemoireRepository repRAM;

    ///////////////////////////////////////// POST //////////////////////////////////////////

    public CreateCPUResponse saveCPU(CreateCPURequest request) {
        Processeur toCreate = Processeur.builder()
                .frequence(request.getFrequence())
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .nbCoeurs(request.getNbcoeurs())
                .socket(request.getSocket())
                .url(request.getUrl())
                .build();
        return buildCreateCPUResponse(this.repCompo.save(toCreate));
    }

    private CreateCPUResponse buildCreateCPUResponse(Processeur save) {
        return CreateCPUResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .socket(save.getSocket())
                .nbcoeurs(save.getNbCoeurs())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .frequence(save.getFrequence())
                .id(save.getIdComposant())
                .build();
    }

    public CreateGPUResponse saveGPU(CreateGPURequest request) {
        CarteGraphique toCreate = CarteGraphique.builder()
                .frequence(request.getFrequence())
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .nbVentilateurs(request.getNbventilateurs())
                .url(request.getUrl())
                .vRam(request.getVram())
                .build();
        return buildCreateGPUResponse(this.repGraph.save(toCreate));
    }

    private CreateGPUResponse buildCreateGPUResponse(CarteGraphique save) {
        return CreateGPUResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .nbVentilateurs(save.getNbVentilateurs())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .frequence(save.getFrequence())
                .vram(save.getVRam())
                .id(save.getIdComposant())
                .build();
    }

    public CreateAlimResponse saveAlim(CreateAlimRequest request){
        Alimentation toCreate = Alimentation.builder()
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .puissance(request.getPuissance())
                .url(request.getUrl())
                .rendement(request.getRendement())
                .build();
        return buildCreateAlimResponse(this.repAlim.save(toCreate));
    }

    private CreateAlimResponse buildCreateAlimResponse(Alimentation save){
        return CreateAlimResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .rendement(save.getRendement())
                .puissance(save.getPuissance())
                .id(save.getIdComposant())
                .build();
    }

    public CreateBoitierResponse saveBoitier(CreateBoitierRequest request){
        Boitier toCreate = Boitier.builder()
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .rgb(request.getRgb())
                .url(request.getUrl())
                .ventilateursInclus(request.getVentilateursInclus())
                .taille(request.getTaille())
                .build();
        return buildCreateBoitierResponse(this.repBoitier.save(toCreate));
    }

    private CreateBoitierResponse buildCreateBoitierResponse(Boitier save){
        return CreateBoitierResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .rgb(save.getRgb())
                .taille(save.getTaille())
                .ventilateursInclus(save.getVentilateursInclus())
                .id(save.getIdComposant())
                .build();
    }

    public CreateCarteMereResponse saveCarteMere(CreateCarteMereRequest request){
        CarteMere toCreate = CarteMere.builder()
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .nbBarrettes(request.getNbBarrettes())
                .url(request.getUrl())
                .socket(request.getSocket())
                .taille(request.getTaille())
                .build();
        return buildCreateCarteMereResponse(this.repCarteMere.save(toCreate));
    }

    private CreateCarteMereResponse buildCreateCarteMereResponse(CarteMere save){
        return CreateCarteMereResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .socket(save.getSocket())
                .taille(save.getTaille())
                .nbBarettes(save.getNbBarrettes())
                .id(save.getIdComposant())
                .build();
    }

    public CreateDisqueDurHDDResponse saveHDD(CreateDisqueDurHDDRequest request){
        DisqueDurHDD toCreate = DisqueDurHDD.builder()
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .url(request.getUrl())
                .vitesse(request.getVitesse())
                .capacite(request.getCapacite())
                .build();
        return buildCreateDisqueDurHDDResponse(this.repHDD.save(toCreate));
    }

    private CreateDisqueDurHDDResponse buildCreateDisqueDurHDDResponse(DisqueDurHDD save){
        return CreateDisqueDurHDDResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .vitesse(save.getVitesse())
                .capacite(save.getCapacite())
                .id(save.getIdComposant())
                .build();
    }

    public CreateDisqueDurSSDResponse saveSSD(CreateDisqueDurSSDRequest request){
        DisqueDurSSD toCreate = DisqueDurSSD.builder()
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .url(request.getUrl())
                .type(request.getType())
                .capacite(request.getCapacite())
                .build();
        return buildCreateDisqueDurSSDResponse(this.repSSD.save(toCreate));
    }

    private CreateDisqueDurSSDResponse buildCreateDisqueDurSSDResponse(DisqueDurSSD save){
        return CreateDisqueDurSSDResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .type(save.getType())
                .capacite(save.getCapacite())
                .id(save.getIdComposant())
                .build();
    }

    public CreateMemoireResponse saveRAM(CreateMemoireRequest request){
        Memoire toCreate = Memoire.builder()
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .url(request.getUrl())
                .type(request.getType())
                .capacite(request.getCapacite())
                .frequence(request.getFrequence())
                .build();
        return buildCreateMemoireResponse(this.repRAM.save(toCreate));
    }

    private CreateMemoireResponse buildCreateMemoireResponse(Memoire save){
        return CreateMemoireResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .type(save.getType())
                .capacite(save.getCapacite())
                .frequence(save.getFrequence())
                .id(save.getIdComposant())
                .build();
    }

    ///////////////////////////////////// GET /////////////////////////////////////

    private GetCPUResponse buildGetCPUResponse(Processeur cpu) {
        return GetCPUResponse.builder()
                .id(cpu.getIdComposant())
                .description(cpu.getDescription())
                .nbcoeurs(cpu.getNbCoeurs())
                .url(cpu.getUrl())
                .frequence(cpu.getFrequence())
                .marque(cpu.getMarque())
                .nom(cpu.getNom())
                .prix(cpu.getPrix())
                .socket(cpu.getSocket())
                .build();
    }

    private GetGPUResponse buildGetGPUResponse(CarteGraphique gpu) {
        return GetGPUResponse.builder()
                .description(gpu.getDescription())
                .marque(gpu.getMarque())
                .nbventilateurs(gpu.getNbVentilateurs())
                .url(gpu.getUrl())
                .nom(gpu.getNom())
                .prix(gpu.getPrix())
                .frequence(gpu.getFrequence())
                .vram(gpu.getVRam())
                .id(gpu.getIdComposant())
                .build();

    }

    private GetAlimResponse buildGetAlimResponse(Alimentation alim){
        return GetAlimResponse.builder()
                .description(alim.getDescription())
                .marque(alim.getMarque())
                .puissance(alim.getPuissance())
                .rendement(alim.getRendement())
                .url(alim.getUrl())
                .nom(alim.getNom())
                .prix(alim.getPrix())
                .id(alim.getIdComposant())
                .build();
    }

    private GetBoitierResponse buildGetBoitierResponse(Boitier b){
        return GetBoitierResponse.builder()
                .description(b.getDescription())
                .marque(b.getMarque())
                .taille(b.getTaille())
                .ventilateursInclus(b.getVentilateursInclus())
                .url(b.getUrl())
                .nom(b.getNom())
                .prix(b.getPrix())
                .id(b.getIdComposant())
                .rgb(b.getRgb())
                .build();
    }

    private GetCarteMereResponse buildGetCarteMereResponse(CarteMere cm){
        return GetCarteMereResponse.builder()
                .description(cm.getDescription())
                .marque(cm.getMarque())
                .taille(cm.getTaille())
                .socket(cm.getSocket())
                .url(cm.getUrl())
                .nom(cm.getNom())
                .prix(cm.getPrix())
                .id(cm.getIdComposant())
                .nbBarettes(cm.getNbBarrettes())
                .build();
    }

    private GetDisqueDurHDDResponse buildGetHDDResponse(DisqueDurHDD hdd){
        return GetDisqueDurHDDResponse.builder()
                .description(hdd.getDescription())
                .marque(hdd.getMarque())
                .capacite(hdd.getCapacite())
                .vitesse(hdd.getVitesse())
                .url(hdd.getUrl())
                .nom(hdd.getNom())
                .prix(hdd.getPrix())
                .id(hdd.getIdComposant())
                .build();
    }

    private GetDisqueDurSSDResponse buildGetSSDResponse(DisqueDurSSD ssd){
        return GetDisqueDurSSDResponse.builder()
                .description(ssd.getDescription())
                .marque(ssd.getMarque())
                .capacite(ssd.getCapacite())
                .type(ssd.getType())
                .url(ssd.getUrl())
                .nom(ssd.getNom())
                .prix(ssd.getPrix())
                .id(ssd.getIdComposant())
                .build();
    }

    private GetMemoireResponse buildGetRAMResponse(Memoire ram){
        return GetMemoireResponse.builder()
                .description(ram.getDescription())
                .marque(ram.getMarque())
                .capacite(ram.getCapacite())
                .type(ram.getType())
                .frequence(ram.getFrequence())
                .url(ram.getUrl())
                .nom(ram.getNom())
                .prix(ram.getPrix())
                .id(ram.getIdComposant())
                .build();
    }

    public GetComposantsResponse getComposantById(Integer id){
        GetComposantsResponse toReturn = new GetComposantsResponse();
        Optional<Processeur> cpu = this.repProc.findById(id);
        if (cpu.isPresent()){
            toReturn.getProcesseurs().add(buildGetCPUResponse(cpu.get())); return toReturn;
        }
        Optional <CarteGraphique> gpu = this.repGraph.findById(id);
        if (gpu.isPresent()){
            toReturn.getCartesgraphiques().add(buildGetGPUResponse(gpu.get())); return toReturn;
        }
        Optional<Alimentation> alim = this.repAlim.findById(id);
        if (alim.isPresent()){
            toReturn.getAlimentations().add(buildGetAlimResponse(alim.get())); return toReturn;
        }
        Optional<Boitier> boitier = this.repBoitier.findById(id);
        if (boitier.isPresent()){
            toReturn.getBoitiers().add(buildGetBoitierResponse(boitier.get())); return toReturn;
        }
        Optional<CarteMere> carteMere = this.repCarteMere.findById(id);
        if (carteMere.isPresent()){
            toReturn.getCartemeres().add(buildGetCarteMereResponse(carteMere.get())); return toReturn;
        }
        Optional<DisqueDurHDD> hdd = this.repHDD.findById(id);
        if (hdd.isPresent()){
            toReturn.getHdd().add(buildGetHDDResponse(hdd.get())); return toReturn;
        }
        Optional<DisqueDurSSD> ssd = this.repSSD.findById(id);
        if (ssd.isPresent()){
            toReturn.getSsd().add(buildGetSSDResponse(ssd.get())); return toReturn;
        }
        Optional<Memoire> ram = this.repRAM.findById(id);
        if (ram.isPresent()){
            toReturn.getRam().add(buildGetRAMResponse(ram.get())); return toReturn;
        }
        return toReturn;
    }

    public GetComposantsResponse getAllComposantsByFilters(Double prixMin, Double prixMax, String marque, String description,
                                                           String categories, String socket, Integer nbCoeursMin, Integer nbCoeursMax,
                                                           Double frequenceMin, Double frequenceMax, Integer nbVentilateursMin,
                                                           Integer nbVentilateursMax, Double puissanceMin, Double puissanceMax, Double rendementMin,
                                                           Double rendementMax, String taille, Boolean rgb, Boolean ventilateursInclus,
                                                           Integer nbBarrettesMin, Integer nbBarrettesMax, Integer capaciteMin, Integer capaciteMax,
                                                           Integer vitesseMin, Integer vitesseMax, String type) {
        List<Processeur> processeurs; List<CarteGraphique> cartesgraphiques; List<Alimentation> alimentations;
        List<Boitier> boitiers; List<CarteMere> cartesMere; List<DisqueDurHDD> hdds; List<DisqueDurSSD> ssds; List<Memoire> memoires;
        if (categories != null){
            processeurs = categories.contains("cpu") ? this.repProc.findAll() : new ArrayList<>();
            cartesgraphiques = categories.contains("gpu") ? this.repGraph.findAll() : new ArrayList<>();
            alimentations = categories.contains("alim") ? this.repAlim.findAll() : new ArrayList<>();
            boitiers = categories.contains("boitier") ? this.repBoitier.findAll() : new ArrayList<>();
            cartesMere = categories.contains("cartemere") ? this.repCarteMere.findAll() : new ArrayList<>();
            hdds = categories.contains("hdd") ? this.repHDD.findAll() : new ArrayList<>();
            ssds = categories.contains("ssd") ? this.repSSD.findAll() : new ArrayList<>();
            memoires = categories.contains("ram") ? this.repRAM.findAll() : new ArrayList<>();
        } else {
            processeurs = this.repProc.findAll();
            cartesgraphiques = this.repGraph.findAll();
            alimentations = this.repAlim.findAll();
            boitiers = this.repBoitier.findAll();
            cartesMere = this.repCarteMere.findAll();
            hdds = this.repHDD.findAll();
            ssds = this.repSSD.findAll();
            memoires = this.repRAM.findAll();
        }

        if (prixMin != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getPrix() >= prixMin).collect(Collectors.toList());
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getPrix() >= prixMin).collect(Collectors.toList());
            alimentations = alimentations.stream().filter(alim -> alim.getPrix() >= prixMin).collect(Collectors.toList());
            boitiers = boitiers.stream().filter(boitier -> boitier.getPrix() >= prixMin).collect(Collectors.toList());
            cartesMere = cartesMere.stream().filter(cm -> cm.getPrix() >= prixMin).collect(Collectors.toList());
            hdds = hdds.stream().filter(hdd -> hdd.getPrix() >= prixMin).collect(Collectors.toList());
            ssds = ssds.stream().filter(ssd -> ssd.getPrix() >= prixMin).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getPrix() >= prixMin).collect(Collectors.toList());
        }
        if (prixMax != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getPrix() <= prixMax).collect(Collectors.toList());
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getPrix() <= prixMax).collect(Collectors.toList());
            alimentations = alimentations.stream().filter(alim -> alim.getPrix() <= prixMax).collect(Collectors.toList());
            boitiers = boitiers.stream().filter(boitier -> boitier.getPrix() <= prixMax).collect(Collectors.toList());
            cartesMere = cartesMere.stream().filter(cm -> cm.getPrix() <= prixMax).collect(Collectors.toList());
            hdds = hdds.stream().filter(hdd -> hdd.getPrix() <= prixMax).collect(Collectors.toList());
            ssds = ssds.stream().filter(ssd -> ssd.getPrix() <= prixMax).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getPrix() <= prixMax).collect(Collectors.toList());
        }
        if (marque != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getMarque().equals(marque)).collect(Collectors.toList());
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getMarque().equals(marque)).collect(Collectors.toList());
            alimentations = alimentations.stream().filter(alim -> alim.getMarque().equals(marque)).collect(Collectors.toList());
            boitiers = boitiers.stream().filter(boitier -> boitier.getMarque().equals(marque)).collect(Collectors.toList());
            cartesMere = cartesMere.stream().filter(cm -> cm.getMarque().equals(marque)).collect(Collectors.toList());
            hdds = hdds.stream().filter(hdd -> hdd.getMarque().equals(marque)).collect(Collectors.toList());
            ssds = ssds.stream().filter(ssd -> ssd.getMarque().equals(marque)).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getMarque().equals(marque)).collect(Collectors.toList());
        }
        if (description != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getDescription().contains(description)).collect(Collectors.toList());
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getDescription().contains(description)).collect(Collectors.toList());
            alimentations = alimentations.stream().filter(alim -> alim.getDescription().contains(description)).collect(Collectors.toList());
            boitiers = boitiers.stream().filter(boitier -> boitier.getDescription().contains(description)).collect(Collectors.toList());
            cartesMere = cartesMere.stream().filter(cm -> cm.getDescription().contains(description)).collect(Collectors.toList());
            hdds = hdds.stream().filter(hdd -> hdd.getDescription().contains(description)).collect(Collectors.toList());
            ssds = ssds.stream().filter(ssd -> ssd.getDescription().contains(description)).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getDescription().contains(description)).collect(Collectors.toList());
        }
        if (socket != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getSocket().equals(socket)).collect(Collectors.toList());
            cartesMere = cartesMere.stream().filter(cm -> cm.getSocket().equals(socket)).collect(Collectors.toList());
        }
        if (nbCoeursMin != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getNbCoeurs() >= nbCoeursMin).collect(Collectors.toList());
        }
        if (nbCoeursMax != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getNbCoeurs() <= nbCoeursMax).collect(Collectors.toList());
        }
        if (frequenceMin != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getFrequence() >= frequenceMin).collect(Collectors.toList());
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getFrequence() >= frequenceMin).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getFrequence() >= frequenceMin).collect(Collectors.toList());
        }
        if (frequenceMax != null){
            processeurs = processeurs.stream().filter(cpu -> cpu.getFrequence() <= frequenceMax).collect(Collectors.toList());
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getFrequence() <= frequenceMax).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getFrequence() <= frequenceMax).collect(Collectors.toList());
        }
        if (nbVentilateursMin != null){
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getNbVentilateurs() >= nbVentilateursMin).collect(Collectors.toList());
        }
        if (nbVentilateursMax != null){
            cartesgraphiques = cartesgraphiques.stream().filter(gpu -> gpu.getNbVentilateurs() <= nbVentilateursMax).collect(Collectors.toList());
        }
        if (puissanceMin != null){
            alimentations = alimentations.stream().filter(alim -> alim.getPuissance() >= puissanceMin).collect(Collectors.toList());
        }
        if (puissanceMax != null){
            alimentations = alimentations.stream().filter(alim -> alim.getPuissance() <= puissanceMax).collect(Collectors.toList());
        }
        if (rendementMin != null){
            alimentations = alimentations.stream().filter(alim -> alim.getRendement() >= rendementMin).collect(Collectors.toList());
        }
        if (rendementMax != null){
            alimentations = alimentations.stream().filter(alim -> alim.getRendement() <= rendementMax).collect(Collectors.toList());
        }
        if (taille != null){
            boitiers = boitiers.stream().filter(b -> b.getTaille().equals(taille)).collect(Collectors.toList());
            cartesMere = cartesMere.stream().filter(cm -> cm.getTaille().equals(taille)).collect(Collectors.toList());
        }
        if (rgb != null){
            boitiers = boitiers.stream().filter(b -> b.getRgb() == rgb).collect(Collectors.toList());
        }
        if (ventilateursInclus != null){
            boitiers = boitiers.stream().filter(b -> b.getVentilateursInclus() == ventilateursInclus).collect(Collectors.toList());
        }
        if (nbBarrettesMin != null){
            cartesMere = cartesMere.stream().filter(cm -> cm.getNbBarrettes() >= nbBarrettesMin).collect(Collectors.toList());
        }
        if (nbBarrettesMax != null){
            cartesMere = cartesMere.stream().filter(cm -> cm.getNbBarrettes() <= nbBarrettesMax).collect(Collectors.toList());
        }
        if (capaciteMin != null){
            hdds = hdds.stream().filter(hdd -> hdd.getCapacite() >= capaciteMin).collect(Collectors.toList());
            ssds = ssds.stream().filter(ssd -> ssd.getCapacite() >= capaciteMin).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getCapacite() >= capaciteMin).collect(Collectors.toList());
        }
        if (capaciteMax != null){
            hdds = hdds.stream().filter(hdd -> hdd.getCapacite() <= capaciteMax).collect(Collectors.toList());
            ssds = ssds.stream().filter(ssd -> ssd.getCapacite() <= capaciteMax).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getCapacite() <= capaciteMax).collect(Collectors.toList());
        }
        if (vitesseMin != null){
            hdds = hdds.stream().filter(hdd -> hdd.getVitesse() >= vitesseMin).collect(Collectors.toList());
        }
        if (vitesseMax != null){
            hdds = hdds.stream().filter(hdd -> hdd.getVitesse() <= vitesseMax).collect(Collectors.toList());
        }
        if (type != null){
            ssds = ssds.stream().filter(ssd -> ssd.getType().equals(type)).collect(Collectors.toList());
            memoires = memoires.stream().filter(ram -> ram.getType().equals(type)).collect(Collectors.toList());
        }

        return GetComposantsResponse.builder()
                .processeurs(processeurs.stream().map(this::buildGetCPUResponse).collect(Collectors.toList()))
                .cartesgraphiques(cartesgraphiques.stream().map(this::buildGetGPUResponse).collect(Collectors.toList()))
                .alimentations(alimentations.stream().map(this::buildGetAlimResponse).collect(Collectors.toList()))
                .boitiers(boitiers.stream().map(this::buildGetBoitierResponse).collect(Collectors.toList()))
                .cartemeres(cartesMere.stream().map(this::buildGetCarteMereResponse).collect(Collectors.toList()))
                .hdd(hdds.stream().map(this::buildGetHDDResponse).collect(Collectors.toList()))
                .ssd(ssds.stream().map(this::buildGetSSDResponse).collect(Collectors.toList()))
                .ram(memoires.stream().map(this::buildGetRAMResponse).collect(Collectors.toList()))
                .build();
    }

    ///////////////////////////////////////// PUT ////////////////////////////////////

    public ModifyCPUResponse modifyCPU(ModifyCPURequest request) throws Exception {
        if (this.repProc.findById(request.getIdComposant()).isEmpty()){
            throw new Exception("204 Item Not Found");
        }
        Processeur toCreate = Processeur.builder()
                .frequence(request.getFrequence())
                .nom(request.getNom())
                .prix(request.getPrix())
                .description(request.getDescription())
                .marque(request.getMarque())
                .nbCoeurs(request.getNbcoeurs())
                .socket(request.getSocket())
                .url(request.getUrl())
                .idComposant(request.getIdComposant())
                .build();
        return buildModifyCPUResponse(this.repProc.save(toCreate));
    }

    private ModifyCPUResponse buildModifyCPUResponse(Processeur save) {
        return ModifyCPUResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .socket(save.getSocket())
                .nbcoeurs(save.getNbCoeurs())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .frequence(save.getFrequence())
                .id(save.getIdComposant())
                .build();
    }
}
