package fr.serveurregistrecompte.controllers;

import fr.serveurregistrecompte.commun.exceptions.ErrorMessageClientException;
import fr.serveurregistrecompte.commun.exceptions.ErrorMessageCompteException;
import fr.serveurregistrecompte.commun.exceptions.ExceptionBadRequest;
import fr.serveurregistrecompte.commun.exceptions.ExceptionNotFound;
import fr.serveurregistrecompte.services.CompteService;
import fr.serveurregistrecompte.services.dtoCompte.CreateCompteRequest;
import fr.serveurregistrecompte.services.dtoCompte.ModifyCompteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("comptes")
/**
 * Le controller des comptes, c'est lui qui va communiquer avec notre base de données de compte
 * (supposément dans le cas ou il y a une base pour les comptes clients à part) et le client.
 * De cette manière, on évite de déplacer trop de donner et on ne récupère côté client que le nécessaire.
 */
public class CompteController {
    @Autowired
    private CompteService compteService;

    @PostMapping
    public ResponseEntity createCompte(@RequestBody CreateCompteRequest compteRequest) throws Exception{
        try {
            return ResponseEntity.ok().body(this.compteService.saveCompte(compteRequest));
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity getComptes(@RequestParam(value = "email", required = false) String email,
                                     @RequestParam(value = "password", required = false) String password) throws Exception {
        try {
            return ResponseEntity.ok().body(this.compteService.GetCompte(email, password));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity updateCompte(@RequestBody ModifyCompteRequest compteRequest) throws Exception{
        try{
            return ResponseEntity.ok().body(this.compteService.updateClient(compteRequest));
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessageClientException(e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageClientException(e.getMessage()));
        }

    }

}
