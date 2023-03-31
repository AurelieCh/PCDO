package fr.serveurregistrecomposants.services;

import fr.serveurregistrecomposants.commun.CarteGraphique;
import fr.serveurregistrecomposants.commun.Processeur;
import fr.serveurregistrecomposants.commun.dto.get.GetCPUResponse;
import fr.serveurregistrecomposants.commun.dto.get.GetComposantsResponse;
import fr.serveurregistrecomposants.commun.dto.get.GetGPUResponse;
import fr.serveurregistrecomposants.commun.dto.post.*;
import fr.serveurregistrecomposants.repository.CarteGraphiqueRepository;
import fr.serveurregistrecomposants.repository.ComposantRepository;
import fr.serveurregistrecomposants.repository.ProcesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Service
public class ComposantService {
    @Autowired
    private ComposantRepository repCompo;
    @Autowired
    private ProcesseurRepository repProc;
    @Autowired
    private CarteGraphiqueRepository repGraph;

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
        return buildCPUResponse(this.repProc.save(toCreate));
    }

    private CreateCPUResponse buildCPUResponse(Processeur save) {
        return CreateCPUResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .socket(save.getSocket())
                .nbcoeurs(save.getNbCoeurs())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .frequence(save.getFrequence())
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
        return buildGPUResponse(this.repGraph.save(toCreate));
    }

    private CreateGPUResponse buildGPUResponse(CarteGraphique save) {
        return CreateGPUResponse.builder()
                .description(save.getDescription())
                .marque(save.getMarque())
                .nbVentilateurs(save.getNbVentilateurs())
                .url(save.getUrl())
                .nom(save.getNom())
                .prix(save.getPrix())
                .frequence(save.getFrequence())
                .vram(save.getVRam())
                .build();
    }

    public GetCPUResponse getCPU(Integer id) throws Exception {
        Optional<Processeur> temp = this.repProc.findById(id);
        if (temp.isEmpty()){
            throw new Exception("Pas de processeur trouvé");
        }
        Processeur p = temp.get();
        return GetCPUResponse.builder()
                .id(id)
                .description(p.getDescription())
                .nbcoeurs(p.getNbCoeurs())
                .url(p.getUrl())
                .frequence(p.getFrequence())
                .marque(p.getMarque())
                .nom(p.getNom())
                .prix(p.getPrix())
                .socket(p.getSocket())
                .build();
    }

    public GetComposantsResponse getAllComposants() {
        List<Processeur> processeurs = this.repProc.findAll();
        List<CarteGraphique> cartesgraphiques = this.repGraph.findAll();

        return GetComposantsResponse.builder()
                .processeurs(processeurs.stream().map(this::buildGetCPUResponse).collect(Collectors.toList()))
                .cartesgraphiques(cartesgraphiques.stream().map(this::buildGetGPUResponse).collect(Collectors.toList()))
                .build();
    }

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
}
