package fr.serveurregistrecommandes.controllers;

import fr.serveurregistrecommandes.exceptions.ErrorMessage;
import fr.serveurregistrecommandes.exceptions.ExceptionBadRequest;
import fr.serveurregistrecommandes.exceptions.ExceptionNotFound;
import fr.serveurregistrecommandes.services.CommandeService;
import fr.serveurregistrecommandes.services.dto.CreateCommandeRequest;
import fr.serveurregistrecommandes.services.dto.UpdateCommandeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    /**
     *
     * @param createCommandeRequest
     * @return
     * @throws Exception
     *
     * Fonction qui crée une commande et l'associe à un compte, si l'email donné n'est pas dans la base de données
     * des comptes, on retourne une 204, si l'un des champs est vide, ou si un des composants listé
     * n'existe pas dans la base ded composants, on retourne une 400.
     */
    @PostMapping("createCommande")
    public ResponseEntity createCommande(@RequestBody CreateCommandeRequest createCommandeRequest) throws Exception{
        try {
            return ResponseEntity.ok().body(this.commandeService.CreateCommande(createCommandeRequest));
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }

    /**
     *
     * @param updateCommandeRequest
     * @return
     * @throws Exception
     *
     * Fonction qui permet de mettre à jour le status d'une commande.
     */
    @PutMapping
    public ResponseEntity updateCommande(@RequestBody UpdateCommandeRequest updateCommandeRequest) throws Exception{
        try{
            return ResponseEntity.ok().body(this.commandeService.updateCommande(updateCommandeRequest));
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }

    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     *
     * Fonction qui récupère une commande à partir d'un id, si la commande n'existe pas on retourne une
     * 204.
     */
    @GetMapping("getCommande")
    public ResponseEntity getCommande(@RequestParam("id") Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.commandeService.getCommande(id));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        }  catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
}
