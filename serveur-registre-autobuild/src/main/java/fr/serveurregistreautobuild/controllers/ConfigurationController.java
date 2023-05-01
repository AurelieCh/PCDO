package fr.serveurregistreautobuild.controllers;

import fr.serveurregistreautobuild.commun.exceptions.ServiceUnavailable;
import fr.serveurregistreautobuild.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
