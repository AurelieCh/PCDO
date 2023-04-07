package fr.serveurregistrecompte.controllers;

import fr.serveurregistrecompte.commun.exceptions.ErrorMessageCompteException;
import fr.serveurregistrecompte.commun.exceptions.ExceptionBadRequest;
import fr.serveurregistrecompte.commun.exceptions.ExceptionNotFound;
import fr.serveurregistrecompte.services.CompteService;
import fr.serveurregistrecompte.services.dtoCompte.CreateCompteRequest;
import fr.serveurregistrecompte.services.dtoCompte.DeleteCompteRequest;
import fr.serveurregistrecompte.services.dtoCompte.GetCompteRequest;
import fr.serveurregistrecompte.services.dtoCompte.ModifyCompteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
/**
 * Le controller des comptes, c'est lui qui va communiquer avec notre base de données de compte
 * (supposément dans le cas ou il y a une base pour les comptes clients à part) et le client.
 * De cette manière, on évite de déplacer trop de donner et on ne récupère côté client que le nécessaire.
 */
public class CompteController {
    @Autowired
    private CompteService compteService;

    /**
     *
     * @param compteRequest
     * @return
     * @throws Exception
     *
     * Fonction qui sert à créer un compte, elle retourne une bad request si l'un des champs est vide.
     */
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

    /**
     *
     * @param getCompteRequest
     * @return
     * @throws Exception
     *
     * Récupère un compte avec' l'email (clé primaire) et le mot de passe, retourne 204 si aucun compte
     * n'est lié à l'email et 400 si un des champs est vide ou si le mot de passe ne correspond pas à
     * celui du compte.
     */
    @GetMapping("getCompte")
    public ResponseEntity getComptes(@RequestBody GetCompteRequest getCompteRequest) throws Exception {
        try {
            return ResponseEntity.ok().body(this.compteService.GetCompte(getCompteRequest));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }
    }

    /**
     *
     * @param compteRequest
     * @return
     * @throws Exception
     *
     * Met à jour un compte (nom, prénom, adresse, etc.) retourne un 204 si aucun compte n'est lié
     * à l'email donné, et 400 si un champ est vide ou si le mot de passe ne correspond pas à celui
     * du compte.
     */
    @PutMapping
    public ResponseEntity updateCompte(@RequestBody ModifyCompteRequest compteRequest) throws Exception{
        try{
            return ResponseEntity.ok().body(this.compteService.updateCompte(compteRequest));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }

    }

    /**
     *
     * @param deleteCompteRequest
     * @return
     * @throws Exception
     *
     * Fonction qui sert à supprimer un compte (retourne 400 si le mot de passe ne correspond pas,
     * 204 si l'email n'existe pas dans la base).
     */
    @DeleteMapping()
    public ResponseEntity delCompte(@RequestBody DeleteCompteRequest deleteCompteRequest) throws Exception{
        try{
            return ResponseEntity.ok().body(this.compteService.delCompte(deleteCompteRequest));
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }
    }


    /**
     *
     * @param id
     * @param email
     * @return
     * @throws Exception
     *
     * Pour cette fonction, il n'y a pas beaucoup de gestion d'évènements, car elles ne sont utilisées que par
     * d'autres services, qui font avant d'utiliser la fonction les vérifications nécessaires. (204 et 400 notamment)
     */
    @PutMapping("addFacture")
    public ResponseEntity addFacture(@RequestParam(value = "id", required = true) Integer id,
                                     @RequestParam(value = "email", required = true) String email) throws Exception{
        try{
            return ResponseEntity.ok().body(this.compteService.AddFacture(id, email));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }

    }

    /**
     *
     * @param id
     * @param email
     * @return
     * @throws Exception
     *
     * Pour cette fonction, il n'y a pas beaucoup de gestion d'évènements, car elles ne sont utilisées que par
     * d'autres services, qui font avant d'utiliser la fonction les vérifications nécessaires. (204 et 400 notamment)
     */
    @PutMapping("addCommande")
    public ResponseEntity addCommande(@RequestParam(value = "id", required = true) Integer id,
                                     @RequestParam(value = "email", required = true) String email) throws Exception{
        try{
            return ResponseEntity.ok().body(this.compteService.AddCommande(id, email));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }

    }

    /**
     *
     * @param email
     * @return
     * @throws Exception
     *
     * Fonction qui vérifie si un compte lié à une email existe (retourne true si oui, 204 sinon).
     */
    @GetMapping("checkMail")
    public ResponseEntity verifyCompte(@RequestParam(value = "email", required = true) String email) throws Exception{
        try{
            return ResponseEntity.ok().body(this.compteService.verifyCompte(email));
        } catch (ExceptionBadRequest e) {
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }
    }

    /**
     *
     * @param id
     * @param email
     * @return
     * @throws Exception
     *
     * Pour cette fonction, il n'y a pas beaucoup de gestion d'évènements, car elles ne sont utilisées que par
     * d'autres services, qui font avant d'utiliser la fonction les vérifications nécessaires. (204 et 400 notamment)
     */
    @PutMapping("updatePanier")
    public ResponseEntity updatePanier(@RequestParam(value = "compo", required = true) List<Integer> id,
                                     @RequestParam(value = "email", required = true) String email) throws Exception{
        try {
            return ResponseEntity.ok().body(this.compteService.updatePanier(id, email));
        } catch (ExceptionBadRequest e) {
            return ResponseEntity.badRequest().body(new ErrorMessageCompteException(e.getMessage()));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessageCompteException(e.getMessage()));
        }
    }

}
