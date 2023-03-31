package fr.serveurregistrecomposants.controllers;

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

    @GetMapping()
    public ResponseEntity getAllComposants(){
        try {
            return ResponseEntity.ok().body(this.serCompo.getAllComposants());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
