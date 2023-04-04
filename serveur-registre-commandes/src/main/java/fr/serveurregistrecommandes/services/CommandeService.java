package fr.serveurregistrecommandes.services;

import fr.serveurregistrecommandes.commun.Commande;
import fr.serveurregistrecommandes.commun.Mail;
import fr.serveurregistrecommandes.commun.Producer;
import fr.serveurregistrecommandes.commun.types.Status;
import fr.serveurregistrecommandes.commun.types.TypeMail;
import fr.serveurregistrecommandes.exceptions.ExceptionBadRequest;
import fr.serveurregistrecommandes.exceptions.ExceptionNotFound;
import fr.serveurregistrecommandes.repositories.CommandeRepository;
import fr.serveurregistrecommandes.services.dto.*;
import fr.serveurregistrecommandes.services.dto.composants.GetComposantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Producer producer;

    /**
     *
     * @param createCommandeRequest
     * @return CreateCommandeResponse
     *
     * Fonction qui vérifie les champs envoyés par l'utilisateur et qui sauvegarde
     * une commande dans le repository des commandes.
     *
     */
    public CreateCommandeResponse CreateCommande(CreateCommandeRequest createCommandeRequest) throws ExceptionBadRequest, ExceptionNotFound{
        if(createCommandeRequest.getAdresse().isEmpty()
        || createCommandeRequest.getAdresse().isBlank()
        || createCommandeRequest.getEmail().isBlank()
        || createCommandeRequest.getEmail().isEmpty()
        || createCommandeRequest.getComposants().isEmpty()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "L'une des données n'est pas présentes ou est incorrectes. Erreur 400");
        }

        //Premier compte fait référence au "localhost" -> Nom de l'appli.
        ResponseEntity<String> response = restTemplate.getForEntity("http://ms-comptes/comptes/checkMail?email=" + createCommandeRequest.getEmail(), String.class);

        if (!(response.getStatusCode() == HttpStatus.OK)) {
            throw new ExceptionNotFound("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Aucun compte trouvé avec cette email. Erreur 204");
        }

        ResponseEntity<String> response2;
        Double prix = 0.0;
        GetComposantResponse g;
        for(Integer composant : createCommandeRequest.getComposants()){
            try {
                g = restTemplate.getForObject("http://ms-composants/composants/" + composant, GetComposantResponse.class); //Besoin d'un retour de type 200 avec le prix
            } catch (Exception e) {
                throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                        "L'un des composants n'existe pas dans la base (mauvais id). Erreur 400");
            }

            prix += g.getPrix();
        }

        int rand = (int) (Math.random() * ( 3 - 0 ));

        Commande toCreate;
        toCreate = Commande.builder()
                .prix(Math.round(prix*100.0)/100.0)
                .dateCommande(new Date())
                .adresse(createCommandeRequest.getAdresse())
                .email(createCommandeRequest.getEmail())
                .composants(createCommandeRequest.getComposants())
                .status(Status.Prise_en_compte)
                .build();
        toCreate = this.commandeRepository.save(toCreate);

        //Premier compte fait référence au "localhost" -> Nom de l'appli.
        restTemplate.put("http://ms-comptes/comptes/addCommande?id="+toCreate.getId()+"&email="+toCreate.getEmail(), String.class);

        // Créer un objet représentant le corps de la requête (ici, une chaîne de caractères)
        String requestBody = "{" +
                "\"email\":"            + "\"" + toCreate.getEmail()        + "\"" + "," +
                "\"commande\":"         +        toCreate.getId()           +        "," +
                "\"adresse\":"          + "\"" + toCreate.getAdresse()      + "\"" + "," +
                "\"prix\":"             + "\"" + toCreate.getPrix()         + "\"" + "," +
                "\"typePaiement\":"     +        rand                       +
                "}";

        // Créer un objet représentant les en-têtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Créer un objet représentant la requête HTTP
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        // Exécuter la requête POST en utilisant RestTemplate
        String url = "http://ms-facturations/facturations";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        if(!(responseEntity.getStatusCode() == HttpStatus.OK)){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "La facture n'a pas pu être crée. Erreur 400");
        }

        this.producer.send(Mail.builder().typeMail(TypeMail.Prise_en_compte).emailDesti(toCreate.getEmail()).build());
        return buildCreateCommandeResponse(toCreate);
    }

    /**
     *
     * @param c
     * @return CreateCommandeResponse
     *
     * Fonction utilisée dans CreateCommande et qui construit l'objet retourné à l'utilisateur.
     *
     */
    private CreateCommandeResponse buildCreateCommandeResponse(Commande c){
        return CreateCommandeResponse.builder()
                .id(c.getId())
                .adresse(c.getAdresse())
                .composants(c.getComposants())
                .dateCommande(c.getDateCommande())
                .prix(c.getPrix())
                .status(c.getStatus())
                .build();
    }

    /**
     *
     * @param updateCommandeRequest
     * @return UpdateCommandeRepsonse
     *
     * Fonction qui vérifie les champs envoyés par l'utilisateur et qui modifie l'attribut status d'une
     * commande si celle-ci existe.
     *
     */
    public UpdateCommandeResponse updateCommande(UpdateCommandeRequest updateCommandeRequest) throws ExceptionBadRequest, ExceptionNotFound {
        if(updateCommandeRequest.getId().toString().isEmpty()
        || updateCommandeRequest.getStatus().toString().isEmpty()
        || updateCommandeRequest.getId().toString().isBlank()
        || updateCommandeRequest.getStatus().toString().isBlank()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "L'une des données n'est pas présentes ou est incorrectes. Erreur 400");
        }
        Optional<Commande> temp = this.commandeRepository.findById(updateCommandeRequest.getId());
        if(temp.isEmpty()){
            throw new ExceptionNotFound("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Aucune commande trouvé avec cet id. Erreur 204");
        }
        Commande toSave = temp.get();
        toSave.setStatus(updateCommandeRequest.getStatus());
        TypeMail t = TypeMail.Prise_en_compte;
        switch (toSave.getStatus()){
            case Prise_en_compte :
                t = TypeMail.Prise_en_compte;
                break;
            case Valide :
                t = TypeMail.Valide;
                break;
            case Prepare :
                t = TypeMail.Prepare;
                break;
            case Expedie :
                t = TypeMail.Expedie;
                break;
            case Livre :
                t = TypeMail.Livre;
                break;
        }
        this.producer.send(Mail.builder().typeMail(t).emailDesti(toSave.getEmail()).build());
        return buildUpdateCommandeResponse(this.commandeRepository.save(toSave));
    }

    /**
     *
     * @param c
     * @return UpdateCommandeResponse
     *
     * Fonction utilisée dans updateCommande et qui crée l'objet retourné à l'utilisateur.
     *
     */
    private UpdateCommandeResponse buildUpdateCommandeResponse(Commande c){
        return UpdateCommandeResponse.builder()
                .id(c.getId())
                .status(c.getStatus())
                .build();
    }


    /**
     *
     * @param id
     * @return GetCommandeResponse
     *
     * Fonction qui vérifie les données entrées par l'utilisateur et qui utilise
     * la fonction buildGetCommandeResponse pour retourner à l'utilisateur
     * la commande, sous reserve que celle-ci existe.
     *
     */
    public GetCommandeResponse getCommande(Integer id) throws ExceptionNotFound {
        Optional<Commande> c1 = this.commandeRepository.findById(id);
        if(c1.isEmpty()){
            throw new ExceptionNotFound("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Aucune commande trouvé avec cet id. Erreur 204");
        } else {
            Commande c = c1.get();
            return buildGetCommandeResponse(c);
        }
    }

    /**
     *
     * @param c
     * @return GetCommandeResponse
     *
     * Fonction utilisée dans getCommande et qui crée l'objet retourné à l'utilisateur.
     *
     */
    public GetCommandeResponse buildGetCommandeResponse(Commande c){
        return GetCommandeResponse.builder()
                .id(c.getId())
                .dateCommande(c.getDateCommande())
                .adresse(c.getAdresse())
                .composants(c.getComposants())
                .email(c.getEmail())
                .prix(c.getPrix())
                .status(c.getStatus())
                .build();
    }

    /**
     *
     * @return Status
     *
     * Fonction qui retourne un status aléatoire à l'utilisateur.
     *
     */
    private Status getStatus(){
        Integer rand = (int) (Math.random() * ( 4 - 0 ));
        Status status;
        switch(rand){
            case 0:
                status = Status.Prise_en_compte;
                return status;
            case 1:
                status = Status.Valide;
                return status;
            case 2:
                status = Status.Prepare;
                return status;
            case 3:
                status = Status.Expedie;
                return status;
            case 4:
                status = Status.Livre;
                return status;
            default:
                status = Status.Prise_en_compte;
                return status;
        }
    }
}
