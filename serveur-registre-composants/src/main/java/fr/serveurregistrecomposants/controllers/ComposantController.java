package fr.serveurregistrecomposants.controllers;

import fr.serveurregistrecomposants.commun.dto.post.*;
import fr.serveurregistrecomposants.services.ComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ComposantController {
    @Autowired
    private ComposantService serCompo;


    ///////////////////////////////////////////// POST ///////////////////////////////////////////////

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

    @PostMapping("/alim")
    public ResponseEntity createAlim(@RequestBody CreateAlimRequest alimRequest){
        try {
            if (alimRequest.getDescription() == null || alimRequest.getPrix() == null || alimRequest.getNom() == null ||
                    alimRequest.getUrl() == null || alimRequest.getMarque() == null ||
                    alimRequest.getPuissance() == null || alimRequest.getRendement() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveAlim(alimRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/boitier")
    public ResponseEntity createBoitier(@RequestBody CreateBoitierRequest boitierRequest){
        try {
            if (boitierRequest.getDescription() == null || boitierRequest.getPrix() == null || boitierRequest.getNom() == null ||
                    boitierRequest.getUrl() == null || boitierRequest.getMarque() == null ||
                    boitierRequest.getTaille() == null || boitierRequest.getRgb() == null || boitierRequest.getVentilateursInclus() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveBoitier(boitierRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/cartemere")
    public ResponseEntity createCarteMere(@RequestBody CreateCarteMereRequest carteMereRequest){
        try {
            if (carteMereRequest.getDescription() == null || carteMereRequest.getPrix() == null || carteMereRequest.getNom() == null ||
                    carteMereRequest.getUrl() == null || carteMereRequest.getMarque() == null ||
                    carteMereRequest.getTaille() == null || carteMereRequest.getSocket() == null || carteMereRequest.getNbBarrettes() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveCarteMere(carteMereRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/hdd")
    public ResponseEntity createHDD(@RequestBody CreateDisqueDurHDDRequest createHDDRequest){
        try {
            if (createHDDRequest.getDescription() == null || createHDDRequest.getPrix() == null || createHDDRequest.getNom() == null ||
                    createHDDRequest.getUrl() == null || createHDDRequest.getMarque() == null ||
                    createHDDRequest.getCapacite() == null || createHDDRequest.getVitesse() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveHDD(createHDDRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/ssd")
    public ResponseEntity createSSD(@RequestBody CreateDisqueDurSSDRequest createSSDRequest){
        try {
            if (createSSDRequest.getDescription() == null || createSSDRequest.getPrix() == null || createSSDRequest.getNom() == null ||
                    createSSDRequest.getUrl() == null || createSSDRequest.getMarque() == null ||
                    createSSDRequest.getCapacite() == null || createSSDRequest.getType() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveSSD(createSSDRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/ram")
    public ResponseEntity createRAM(@RequestBody CreateMemoireRequest createRAMRequest){
        try {
            if (createRAMRequest.getDescription() == null || createRAMRequest.getPrix() == null || createRAMRequest.getNom() == null ||
                    createRAMRequest.getUrl() == null || createRAMRequest.getMarque() == null ||
                    createRAMRequest.getFrequence() == null || createRAMRequest.getType() == null || createRAMRequest.getCapacite() == null){
                return ResponseEntity.badRequest().body("Requête pas au bon format");
            }
            return ResponseEntity.created(null).body(this.serCompo.saveRAM(createRAMRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    ///////////////////////////////////////////// GET ///////////////////////////////////////////////

    @GetMapping()
    public ResponseEntity getComposants(@RequestParam(required = false) Integer id,
                                        @RequestParam(required = false) Double prixMin,
                                        @RequestParam(required = false) Double prixMax,
                                        @RequestParam(required = false) String marque,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) String categories,

                                        @RequestParam(required = false) String socket,
                                        @RequestParam(required = false) Integer nbCoeursMin,
                                        @RequestParam(required = false) Integer nbCoeursMax,
                                        @RequestParam(required = false) Double frequenceMin,
                                        @RequestParam(required = false) Double frequenceMax,
                                        @RequestParam(required = false) Integer nbVentilateursMin,
                                        @RequestParam(required = false) Integer nbVentilateursMax,
                                        @RequestParam(required = false) Double puissanceMin,
                                        @RequestParam(required = false) Double puissanceMax,
                                        @RequestParam(required = false) Double rendementMin,
                                        @RequestParam(required = false) Double rendementMax,
                                        @RequestParam(required = false) String taille,
                                        @RequestParam(required = false) Boolean rgb,
                                        @RequestParam(required = false) Boolean ventilateursInclus,
                                        @RequestParam(required = false) Integer nbBarrettesMin,
                                        @RequestParam(required = false) Integer nbBarrettesMax,
                                        @RequestParam(required = false) Integer capaciteMin,
                                        @RequestParam(required = false) Integer capaciteMax,
                                        @RequestParam(required = false) Integer vitesseMin,
                                        @RequestParam(required = false) Integer vitesseMax,
                                        @RequestParam(required = false) String type){
        if (id != null){
            return ResponseEntity.ok().body(this.serCompo.getComposantById(id));
        }
        return ResponseEntity.ok().body(this.serCompo.getAllComposantsByFilters(prixMin, prixMax, marque, description,
                categories, socket, nbCoeursMin, nbCoeursMax, frequenceMin, frequenceMax, nbVentilateursMin, nbVentilateursMax,
                puissanceMin, puissanceMax, rendementMin, rendementMax, taille, rgb, ventilateursInclus,nbBarrettesMin, nbBarrettesMax,
                capaciteMin, capaciteMax,vitesseMin, vitesseMax,type));
    }
}
