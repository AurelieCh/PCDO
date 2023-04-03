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
