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

<<<<<<< Updated upstream
import java.net.URI;
=======
>>>>>>> Stashed changes
import java.util.Date;
import java.util.Optional;

@Service
public class FacturationService {
    @Autowired
    private FacturationRepository facturationRepository;

    @Autowired
    private RestTemplate restTemplate;

<<<<<<< Updated upstream
=======
    /**
     *
     * @param factureRequest
     * @return CreateFactureResponse
     *
     * Fonction qui vérifie les champs envoyés par l'utilisateur et qui sauvegarde
     * une facture dans le repository des factures.
     *
     */
>>>>>>> Stashed changes
    public CreateFactureResponse saveFacture(CreateFactureRequest factureRequest) throws Exception {
        if(factureRequest.getAdresse().isBlank()
                || factureRequest.getAdresse().isEmpty()
                || factureRequest.getCommande().toString().isEmpty()
                || factureRequest.getCommande().toString().isBlank()
                || factureRequest.getEmail().isBlank()
                || factureRequest.getEmail().isEmpty()) {
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "L'une des données n'est pas présentes ou est incorrectes. Erreur 400");
        }

        //Premier compte fait référence au "localhost" -> Nom de l'appli.
<<<<<<< Updated upstream
        ResponseEntity<String> response = restTemplate.getForEntity("http://comptes/comptes/checkMail?email=" + factureRequest.getEmail(), String.class);
=======
        ResponseEntity<String> response = restTemplate.getForEntity("http://ms-comptes/comptes/checkMail?email=" + factureRequest.getEmail(), String.class);
>>>>>>> Stashed changes

        if (!(response.getStatusCode() == HttpStatus.OK)) {
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Aucun compte trouvé avec cette email. Erreur 400");
        } else {
            Facturation toCreate;
            toCreate = Facturation.builder()
                    .adresse(factureRequest.getAdresse())
                    .commande(factureRequest.getCommande())
                    .dateCreation(new Date())
                    .email(factureRequest.getEmail())
                    .typePaiement(factureRequest.getTypePaiement())
                    .build();

            toCreate = this.facturationRepository.save(toCreate);

            //Premier compte fait référence au "localhost" -> Nom de l'appli.
<<<<<<< Updated upstream
            restTemplate.put("http://comptes/comptes/addFacture?id="+toCreate.getId()+"&email="+toCreate.getEmail(), String.class);
            /**
             * ResponseEntity<Void> envois =
             * if (!(envois.getStatusCode() == HttpStatus.OK)) {
             *                 throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
             *                         "Erreur durant l'ajout de la facture au compte. Erreur 400");
             *             }
            */
=======
            restTemplate.put("http://ms-comptes/comptes/addFacture?id="+toCreate.getId()+"&email="+toCreate.getEmail(), String.class);
>>>>>>> Stashed changes

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
        return CreateFactureResponse.builder()
                .email(f.getEmail())
                .adresse(f.getAdresse())
                .commande(f.getCommande())
                .dateCreation(f.getDateCreation())
                .typePaiement(f.getTypePaiement())
                .build();
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
<<<<<<< Updated upstream
                    "Email inconnu. Erreur 204");
=======
                    "Pas de facture avec cet id. Erreur 204");
>>>>>>> Stashed changes
        } else {
            Facturation toReturn = temp.get();
            return buildGetFactureResponse(toReturn);
        }
    }

<<<<<<< Updated upstream
=======
    /**
     *
     * @param f
     * @return
     *
     * Fonction utilisée dans getFactureRequest et qui crée l'objet de réponse
     * que l'utilisateur va voir.
     *
     */
>>>>>>> Stashed changes
    private GetFactureResponse buildGetFactureResponse(Facturation f){
        return GetFactureResponse.builder()
                .id(f.getId())
                .email(f.getEmail())
                .adresse(f.getAdresse())
                .commande(f.getCommande())
                .dateCreation(f.getDateCreation())
                .typePaiement(f.getTypePaiement())
                .build();
    }

}
