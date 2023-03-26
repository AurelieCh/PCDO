package fr.serveurregistrecomposants.services;

import fr.serveurregistrecomposants.commun.CarteGraphique;
import fr.serveurregistrecomposants.commun.Processeur;
import fr.serveurregistrecomposants.commun.dto.post.CreateCPURequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateCPUResponse;
import fr.serveurregistrecomposants.commun.dto.post.CreateGPURequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateGPUResponse;
import fr.serveurregistrecomposants.repository.CarteGraphiqueRepository;
import fr.serveurregistrecomposants.repository.ComposantRepository;
import fr.serveurregistrecomposants.repository.ProcesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .build();
    }
}
