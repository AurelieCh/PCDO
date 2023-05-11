package fr.serveurregistreautobuild.controllers;

import fr.serveurregistreautobuild.commun.dto.PostConfigRequest;
import fr.serveurregistreautobuild.commun.exceptions.BadRequestException;
import fr.serveurregistreautobuild.commun.exceptions.ServiceUnavailable;
import fr.serveurregistreautobuild.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigurationController {
    @Autowired
    private ConfigurationService serConfig;

    @GetMapping("/random")
    private ResponseEntity getConfigRandom() {
        try {
            return ResponseEntity.ok(this.serConfig.randomConfig());
        } catch (ServiceUnavailable e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Le service ms-composants est down.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity getFullConfig(@PathVariable("id") Integer id){
        return this.serConfig.getFullConfig(id);
    }

    @PostMapping
    private ResponseEntity postConfig(@RequestBody PostConfigRequest req){
        if (req.getAlim() == null || req.getBoitier() == null || req.getCpu() == null || req.getGpu() == null ||
            req.getHdd() == null || req.getRam() == null || req.getSsd() == null || req.getCarteMere() == null){
            return ResponseEntity.badRequest().body("Le format de la requête ne correspond pas");
        }
        try {
            return this.serConfig.saveConfig(req);
        } catch (ServiceUnavailable e) {
            return ResponseEntity.internalServerError().body("Le service composants est down");
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
