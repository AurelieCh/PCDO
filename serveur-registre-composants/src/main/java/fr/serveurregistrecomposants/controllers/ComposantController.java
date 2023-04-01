package fr.serveurregistrecomposants.controllers;

import fr.serveurregistrecomposants.commun.dto.get.GetCPUResponse;
import fr.serveurregistrecomposants.commun.dto.get.GetComposantsResponse;
import fr.serveurregistrecomposants.commun.dto.post.CreateCPURequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateGPURequest;
import fr.serveurregistrecomposants.services.ComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping
public class ComposantController {
    @Autowired
    private ComposantService serCompo;

    @PostMapping("/cpu")
    public ResponseEntity createCPU(@RequestBody CreateCPURequest cpuRequest){
        try {
            if (cpuRequest.getDescription() == null || cpuRequest.getPrix() == null || cpuRequest.getNom() == null ||
                    cpuRequest.getUrl() == null || cpuRequest.getMarque() == null || cpuRequest.getSocket() == null ||
                    cpuRequest.getFrequence() == null || cpuRequest.getNbcoeurs() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveCPU(cpuRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/gpu")
    public ResponseEntity createGPU(@RequestBody CreateGPURequest gpuRequest){
        try {
            if (gpuRequest.getDescription() == null || gpuRequest.getPrix() == null || gpuRequest.getNom() == null ||
                    gpuRequest.getUrl() == null || gpuRequest.getMarque() == null ||
                    gpuRequest.getFrequence() == null || gpuRequest.getNbventilateurs() == null || gpuRequest.getVram() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveGPU(gpuRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllComposants(){
        try {
            return ResponseEntity.ok().body(this.serCompo.getAllComposants());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity getComposants(@RequestParam(required = false) Integer id,
                                        @RequestParam(required = false) Double prixMin,
                                        @RequestParam(required = false) Double prixMax,
                                        @RequestParam(required = false) String marque,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) String type,

                                        @RequestParam(required = false) String socket,
                                        @RequestParam(required = false) Integer nbCoeursMin,
                                        @RequestParam(required = false) Integer nbCoeursMax,
                                        @RequestParam(required = false) Double frequenceMin,
                                        @RequestParam(required = false) Double frequenceMax,
                                        @RequestParam(required = false) Integer nbVentilateursMin,
                                        @RequestParam(required = false) Integer nbVentilateursMax
                                        ){
        if (id != null){
            return ResponseEntity.ok().body(this.serCompo.getComposantById(id));
        }
        return ResponseEntity.ok().body(this.serCompo.getAllComposantsByFilters(prixMin, prixMax, marque, description, type, socket, nbCoeursMin, nbCoeursMax, frequenceMin, frequenceMax, nbVentilateursMin, nbVentilateursMax));
    }
}
