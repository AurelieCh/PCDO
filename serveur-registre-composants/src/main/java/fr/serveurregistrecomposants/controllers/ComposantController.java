package fr.serveurregistrecomposants.controllers;

import fr.serveurregistrecomposants.commun.Categorie;
import fr.serveurregistrecomposants.commun.dto.exception.NotFoundException;
import fr.serveurregistrecomposants.commun.dto.put.PutCaracteristiqueRequest;
import fr.serveurregistrecomposants.commun.dto.put.PutComposantRequest;
import fr.serveurregistrecomposants.commun.dto.get.GetComposantRequest;
import fr.serveurregistrecomposants.commun.dto.get.GetComposantResponse;
import fr.serveurregistrecomposants.commun.dto.post.CreateComposantRequest;
import fr.serveurregistrecomposants.services.ComposantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@CrossOrigin("http://localhost:4200")
public class ComposantController {
    @Autowired
    private ComposantService serCompo;
    @PostMapping
    @CrossOrigin("http://localhost:4200")
    private ResponseEntity createComposant(@RequestBody CreateComposantRequest request){
        if (request.getNom() == null || request.getDescription() == null || request.getCaracteristiqueList() == null
                || request.getMarque() == null || request.getPrix() == null || request.getUrl() == null || request.getCategorie() == null)
            return ResponseEntity.badRequest().body("Requête pas au bon format.");
        if (request.getCategorie().equals(Categorie.Processeur)){
            if (!(request.contientCaracteristiques(new String[]{"frequence", "socket", "nbCoeurs"}) && request.getCaracteristiqueList().size() == 3)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un CPU");
            }
        } else if (request.getCategorie().equals(Categorie.MemoireRAM)){
            if (!(request.contientCaracteristiques(new String[]{"frequence", "capacite", "type"}) && request.getCaracteristiqueList().size() == 3)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à de la RAM");
            }
        } else if (request.getCategorie().equals(Categorie.DisqueDurSSD)){
            if (!(request.contientCaracteristiques(new String[]{"capacite", "type"}) && request.getCaracteristiqueList().size() == 2)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un SSD");
            }
        } else if (request.getCategorie().equals(Categorie.DisqueDurHDD)) {
            if (!(request.contientCaracteristiques(new String[]{"capacite", "vitesse"}) && request.getCaracteristiqueList().size() == 2)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un HDD");
            }
        } else if (request.getCategorie().equals(Categorie.CarteMere)) {
            if (!(request.contientCaracteristiques(new String[]{"taille", "socket", "nbBarrettes"}) && request.getCaracteristiqueList().size() == 3)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un GPU");
            }
        } else if (request.getCategorie().equals(Categorie.CarteGraphique)) {
            if (!(request.contientCaracteristiques(new String[]{"nbVentilateurs", "frequence", "vram"}) && request.getCaracteristiqueList().size() == 3)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à une Carte Graphique");
            }
        } else if (request.getCategorie().equals(Categorie.Boitier)) {
            if (!(request.contientCaracteristiques(new String[]{"taille", "rgb", "ventilateursInclus"}) && request.getCaracteristiqueList().size() == 3)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un Boitier");
            }
        } else if (request.getCategorie().equals(Categorie.Alimentation)) {
            if (!(request.contientCaracteristiques(new String[]{"puissance", "rendement"}) && request.getCaracteristiqueList().size() == 2)){
                return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à une Alimentation");
            }
        } else {
            return ResponseEntity.badRequest().body("Catégorie inconnue");
        }
        return ResponseEntity.created(null).body(this.serCompo.saveComposant(request));
    }


    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:4200")
    private ResponseEntity getComposant(@PathVariable("id") Integer id){
        try {
            GetComposantResponse temp = this.serCompo.getComposant(id);
            return temp == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(temp);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }



    @GetMapping
    @CrossOrigin("http://localhost:4200")
    private ResponseEntity getComposant(@RequestBody(required = false) GetComposantRequest request) throws NotFoundException {
        try {
            return ResponseEntity.ok().body(this.serCompo.getComposant(request));
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping
    @CrossOrigin("http://localhost:4200")
    private ResponseEntity modifyComposant(@RequestBody(required = true) PutComposantRequest request){
        try {
            if (request.getNom() == null || request.getDescription() == null || request.getCaracteristiqueList() == null
                    || request.getMarque() == null || request.getPrix() == null || request.getId() == null || request.getUrl() == null || request.getCategorie() == null)
                return ResponseEntity.badRequest().body("Requête pas au bon format.");
            if (request.getCategorie().equals(Categorie.Processeur)){
                if (!(request.contientCaracteristiques(new String[]{"frequence", "socket", "nbCoeurs"}) && request.getCaracteristiqueList().size() == 3)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un CPU");
                }
            } else if (request.getCategorie().equals(Categorie.MemoireRAM)){
                if (!(request.contientCaracteristiques(new String[]{"frequence", "capacite", "type"}) && request.getCaracteristiqueList().size() == 3)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à de la RAM");
                }
            } else if (request.getCategorie().equals(Categorie.DisqueDurSSD)){
                if (!(request.contientCaracteristiques(new String[]{"capacite", "type"}) && request.getCaracteristiqueList().size() == 2)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un SSD");
                }
            } else if (request.getCategorie().equals(Categorie.DisqueDurHDD)) {
                if (!(request.contientCaracteristiques(new String[]{"capacite", "vitesse"}) && request.getCaracteristiqueList().size() == 2)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un HDD");
                }
            } else if (request.getCategorie().equals(Categorie.CarteMere)) {
                if (!(request.contientCaracteristiques(new String[]{"taille", "socket", "nbBarrettes"}) && request.getCaracteristiqueList().size() == 3)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un GPU");
                }
            } else if (request.getCategorie().equals(Categorie.CarteGraphique)) {
                if (!(request.contientCaracteristiques(new String[]{"nbVentilateurs", "frequence", "vram"}) && request.getCaracteristiqueList().size() == 3)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à une Carte Graphique");
                }
            } else if (request.getCategorie().equals(Categorie.Boitier)) {
                if (!(request.contientCaracteristiques(new String[]{"taille", "rgb", "ventilateursInclus"}) && request.getCaracteristiqueList().size() == 3)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à un Boitier");
                }
            } else if (request.getCategorie().equals(Categorie.Alimentation)) {
                if (!(request.contientCaracteristiques(new String[]{"puissance", "rendement"}) && request.getCaracteristiqueList().size() == 2)){
                    return ResponseEntity.badRequest().body("Les caractéristiques données ne correspondent pas à une Alimentation");
                }
            } else {
                return ResponseEntity.badRequest().body("Catégorie inconnue");
            }
            for (PutCaracteristiqueRequest c : request.getCaracteristiqueList()) {
                if (c.getNomCaracteristique() == null || c.getVal() == null)
                    return ResponseEntity.badRequest().body("Requête pas au bon format.");
            }
            return ResponseEntity.ok().body(this.serCompo.modifyComposant(request));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity deleteComposant(@PathVariable Integer id) throws NotFoundException {
        try{
            return ResponseEntity.ok().body(this.serCompo.deleteComposant(id));
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
