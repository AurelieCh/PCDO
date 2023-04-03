package fr.serveurregistrecomposants.controllers;

import fr.serveurregistrecomposants.commun.Categorie;
import fr.serveurregistrecomposants.commun.dto.get.GetComposantRequest;
import fr.serveurregistrecomposants.commun.dto.post.CreateComposantRequest;
import fr.serveurregistrecomposants.services.ComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ComposantController {
    @Autowired
    private ComposantService serCompo;
    @PostMapping
    private ResponseEntity createComposant(@RequestBody CreateComposantRequest request){
        if (request.getCategorie().equals(Categorie.Processeur)){
            if (!request.contientCaracteristiques(new String[]{"frequence", "socket", "nbCoeurs"})){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un CPU");
            }
        } else if (request.getCategorie().equals(Categorie.MemoireRAM)){
            if (!request.contientCaracteristiques(new String[]{"frequence", "capacite", "type"})){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à de la RAM");
            }
        } else if (request.getCategorie().equals(Categorie.DisqueDurSSD)){
            if (!request.contientCaracteristiques(new String[]{"capacite", "type"})){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un SSD");
            }
        } else if (request.getCategorie().equals(Categorie.DisqueDurHDD)) {
            if (!request.contientCaracteristiques(new String[]{"capacite", "vitesse"})) {
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un HDD");
            }
        } else if (request.getCategorie().equals(Categorie.CarteMere)) {
            if (!request.contientCaracteristiques(new String[]{"taille", "socket", "nbBarrettes"})) {
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un GPU");
            }
        } else if (request.getCategorie().equals(Categorie.CarteGraphique)) {
            if (!request.contientCaracteristiques(new String[]{"nbVentilateurs", "frequence", "vram"})) {
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à une Carte Graphique");
            }
        } else if (request.getCategorie().equals(Categorie.Boitier)) {
            if (!request.contientCaracteristiques(new String[]{"taille", "rgb", "ventilateursInclus"})) {
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un Boitier");
            }
        } else if (request.getCategorie().equals(Categorie.Alimentation)) {
            if (!request.contientCaracteristiques(new String[]{"puissance", "rendement"})) {
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à une Alimentation");
            }
        } else {
            return ResponseEntity.badRequest().body("Catégorie inconnue");
        }
        return ResponseEntity.created(null).body(this.serCompo.saveComposant(request));
    }

/*    @GetMapping
    private ResponseEntity getComposant(@RequestBody(required = false) GetComposantRequest request){
        if (request == null){

        }
    }*/
}
