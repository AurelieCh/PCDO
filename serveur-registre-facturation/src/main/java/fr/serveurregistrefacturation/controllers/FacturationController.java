package fr.serveurregistrefacturation.controllers;

import fr.serveurregistrefacturation.exceptions.ErrorMessage;
import fr.serveurregistrefacturation.exceptions.ExceptionBadRequest;
import fr.serveurregistrefacturation.exceptions.ExceptionNotFound;
import fr.serveurregistrefacturation.services.FacturationService;
import fr.serveurregistrefacturation.services.dto.CreateFactureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class FacturationController {
    @Autowired
    private FacturationService facturationService;

    /**
     *
     * @param factureRequest
     * @return
     * @throws Exception
     *
     * Fonction qui crée une facture et l'associe à un compte, si le compte n'existe pas on retourne un 204,
     * si l'un des champs est vide alors on retourne une 400.
     */
    @PostMapping
    public ResponseEntity createFacture(@RequestBody CreateFactureRequest factureRequest) throws Exception{
        try {
            return ResponseEntity.ok().body(this.facturationService.saveFacture(factureRequest));
        } catch (ExceptionBadRequest e){
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     *
     * Récupère un
     */
    @GetMapping("getFacture")
    public ResponseEntity getComptes(@RequestParam("id") Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.facturationService.getFacture(id));
        } catch (ExceptionNotFound e){
            return ResponseEntity.noContent().build();
        }  catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
}
