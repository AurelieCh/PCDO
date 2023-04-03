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

}
