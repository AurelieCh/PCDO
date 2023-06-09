package fr.serveurregistrefacturation.services;

import fr.serveurregistrefacturation.commun.Facturation;
import fr.serveurregistrefacturation.exceptions.ExceptionBadRequest;
import fr.serveurregistrefacturation.exceptions.ExceptionNotFound;
import fr.serveurregistrefacturation.repositories.FacturationRepository;
import fr.serveurregistrefacturation.services.dto.CreateFactureRequest;
import fr.serveurregistrefacturation.services.dto.CreateFactureResponse;
import fr.serveurregistrefacturation.services.dto.GetFactureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class FacturationService {
    @Autowired
    private FacturationRepository facturationRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     * @param factureRequest
     * @return CreateFactureResponse
     *
     * Fonction utilisée dans le micro service des commandes et qui crée et sauvegarde
     * une facture dans le repository des factures et l'associe au compte client.
     *
     */
    public CreateFactureResponse saveFacture(CreateFactureRequest factureRequest) throws Exception {
        if(factureRequest.getAdresse().isBlank()
                || factureRequest.getAdresse().isEmpty()
                || factureRequest.getCommande().toString().isEmpty()
                || factureRequest.getCommande().toString().isBlank()
                || factureRequest.getEmail().isBlank()
                || factureRequest.getEmail().isEmpty()
                || factureRequest.getPrix() == null
                || factureRequest.getTousPrix().isEmpty()) {
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "L'une des données n'est pas présentes ou est incorrectes. Erreur 400");
        } else {
            Facturation toCreate;
            toCreate = Facturation.builder()
                    .adresse(factureRequest.getAdresse())
                    .commande(factureRequest.getCommande())
                    .dateCreation(new Date())
                    .email(factureRequest.getEmail())
                    .tousPrix(new ArrayList<Double>())
                    .prix(factureRequest.getPrix())
                    .typePaiement(factureRequest.getTypePaiement())
                    .build();

            toCreate.setTousPrix(factureRequest.getTousPrix());

            toCreate = this.facturationRepository.save(toCreate);

            //Premier compte fait référence au "localhost" -> Nom de l'appli.
            restTemplate.put("http://ms-comptes/comptes/addFacture?id="+ toCreate.getId() +"&email="+toCreate.getEmail(), String.class);

            return buildCreateFactureResponse(this.facturationRepository.save(toCreate));
        }
    }

    /**
     *
     * @param f
     * @return
     *
     * Fonction utilisée par saveFacture qui crée l'objet de réponse à retourner côté client.
     *
     */
    private CreateFactureResponse buildCreateFactureResponse(Facturation f) {
        CreateFactureResponse toSend = CreateFactureResponse.builder()
                .email(f.getEmail())
                .adresse(f.getAdresse())
                .commande(f.getCommande())
                .dateCreation(f.getDateCreation())
                .tousPrix(new ArrayList<Double>())
                .prix(f.getPrix())
                .typePaiement(f.getTypePaiement())
                .build();

        toSend.setTousPrix(f.getTousPrix());

        return toSend;
    }

    /**
     *
     * @param id
     * @return
     *
     * Fonction utilisée par getFacture qui retourne les données d'une facture.
     *
     */
    public GetFactureResponse getFacture(Integer id) throws ExceptionNotFound {
        Optional<Facturation> temp = this.facturationRepository.findById(id);
        if(temp.isEmpty()){
            throw new ExceptionNotFound("Les données en entrée du service sont non renseignes ou incorrectes." +
                    "Pas de facture avec cet id. Erreur 204");
        } else {
            Facturation toReturn = temp.get();
            return buildGetFactureResponse(toReturn);
        }
    }

    /**
     *
     * @param f
     * @return
     *
     * Fonction utilisée dans getFactureRequest et qui crée l'objet de réponse
     * que l'utilisateur va voir.
     *
     */
    private GetFactureResponse buildGetFactureResponse(Facturation f){
        return GetFactureResponse.builder()
                .id(f.getId())
                .email(f.getEmail())
                .adresse(f.getAdresse())
                .commande(f.getCommande())
                .dateCreation(f.getDateCreation())
                .typePaiement(f.getTypePaiement())
                .tousPrix(f.getTousPrix())
                .prix(f.getPrix())
                .build();
    }

}
