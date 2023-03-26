package fr.serveurregistrecompte.services;

import fr.serveurregistrecompte.commun.Compte;
import fr.serveurregistrecompte.commun.exceptions.ExceptionBadRequest;
import fr.serveurregistrecompte.commun.exceptions.ExceptionNotFound;
import fr.serveurregistrecompte.repositories.CompteRepository;
import fr.serveurregistrecompte.services.dtoCompte.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws Exception
     *
     * Fonction qui récupère les cartes associèes à un compte identifié par son iban
     * après certaines vérifications (liées aux retours d'erreur 400, etc) et utilise la fonction
     * buildGetCartesResponse pour créer et enfin retourner l'objet de réponse côté client.
     *
     */
    public GetCompteResponse GetCompte(String email, String password) throws Exception{
        if(email.isEmpty()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Veuillez entrer un email. Erreur 400");
        }
        if(password.isEmpty()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Veuillez entrer un mot de passe. Erreur 400");
        }
        Optional<Compte> temp = this.compteRepository.findByEmail(email);
        if(temp.isEmpty()) {
            throw new ExceptionNotFound("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "L'email n'est relié à aucun compte. Erreur 404");
        }
        Compte c = temp.get();
        if(!c.getPassword().equals(password)){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Le mot de passe n'est pas le bon. Erreur 404");
        }
        return buildGetCompteResponse(c);
    }

    /**
     *
     * @param c
     * @return
     *
     * Fonction utilisée dans getCompteClient et qui crée l'objet de réponse
     * que l'utilisateur va voir.
     *
     */
    private GetCompteResponse buildGetCompteResponse(Compte c) {
        return GetCompteResponse.builder()
                .nom(c.getNom())
                .prenom(c.getPrenom())
                .email(c.getEmail())
                .adresse(c.getAdresse())
                .password(c.getPassword())
                .panier(c.getPanier())
                .build();
    }

    /**
     *
     * @param compteRequest
     * @return
     * @throws ExceptionBadRequest
     *
     * Fonction qui crée et sauvegarde un nouveau compte à partir des données de la requête.
     * Elle va derechef vérifier les différents paramètres et une fois terminé, retouner l'objet
     * de réponse créé par la fonction buildCreateCompteResponse.
     *
     */
    public CreateCompteResponse saveCompte(CreateCompteRequest compteRequest) throws ExceptionBadRequest, NoSuchAlgorithmException {
        if(compteRequest.getNom().isEmpty() || compteRequest.getNom().isBlank()) {
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Votre nom est obligatoire. Erreur 400");
        }

        if(compteRequest.getPrenom().isEmpty() || compteRequest.getPrenom().isBlank()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Votre prénom est obligatoire. Erreur 400");
        }

        if(compteRequest.getEmail().isEmpty() || compteRequest.getEmail().isBlank()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Votre email est obligatoire. Erreur 400");
        }

        if(this.compteRepository.findByEmail(compteRequest.getEmail()).isPresent()){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Email déjà utilisée. Erreur 400");
        }

        if(compteRequest.getPassword().isEmpty()
                || compteRequest.getPassword().isBlank()
                || compteRequest.getPassword().length() < 8){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Un mot de passe est obligatoire, il doit faire 8 caractères au minimum. Erreur 400");
        }

        if(!compteRequest.getPassword2().equals(compteRequest.getPassword())){
            throw new ExceptionBadRequest("Les données en entrée du service sont non renseignés ou incorrectes. " +
                    "Le mot de passe doit être identique au premier. Erreur 400");
        }

        Compte toCreate = new Compte();
        toCreate = Compte.builder()
                .nom(compteRequest.getNom())
                .prenom(compteRequest.getPrenom())
                .email(compteRequest.getEmail())
                .password(hashMdp(compteRequest.getPassword()))
                .adresse(compteRequest.getAdresse())
                .dateInscription(new Date())
                .build();

        return buildCreateCompteResponse(this.compteRepository.save(toCreate));
    }

    /**
     *
     * @param c
     * @return
     *
     * Fonction utilisée par saveCompte qui crée l'objet de réponse à retourner côté client.
     *
     */
    private CreateCompteResponse buildCreateCompteResponse(Compte c) {
        List<String> panier = new ArrayList<String>();
        return CreateCompteResponse.builder()
                .nom(c.getNom())
                .prenom(c.getPrenom())
                .email(c.getEmail())
                .adresse(c.getAdresse())
                .password(c.getPassword())
                .dateInscription(c.getDateInscription())
                .panier(panier)
                .build();
    }

    /**
     *
     * @param c
     * @return
     * @throws ExceptionBadRequest
     *
     * Fonction qui a partir des informations passées en paramètre
     * (retrouvé içi dans ModifyCompteRequest) va modifier les informations
     * d'un compte client déjà présent dans la base.
     * La fonction va revérifier toutes les informations avant de modifier le client
     * et de retourner au client la réponse via la fonction BuildModifyCompteResponse
     *
     */
    public ModifyCompteResponse updateClient(ModifyCompteRequest c) throws ExceptionBadRequest, ExceptionNotFound {
        if (c.getPrenom().isEmpty()
                || c.getNom().isEmpty()
                || c.getPrenom().isBlank()
                || c.getNom().isBlank()
                || c.getAdresse().isEmpty()
                || c.getAdresse().isBlank()
                || c.getEmail().isEmpty()
                || c.getEmail().isBlank()
                || c.getPassword().isEmpty()
                || c.getPassword().isBlank()){
            throw new ExceptionBadRequest("Les donnnées en entrée du service sont non renseignes ou incorrectes. Un des champs est vide. Erreur 400");
        } else {
            Optional<Compte> temp = compteRepository.findByEmail(c.getEmail());
            if(temp == null) {
                throw new ExceptionBadRequest("Les donnnées en entrée du service sont non renseignes ou incorrectes. Erreur 400");
            }
            Compte c1 = temp.get();
            c1.setNom(c.getNom());
            c1.setPrenom(c.getPrenom());
            c1.setAdresse(c.getAdresse());
            c1.setEmail(c.getEmail());
            c1.setPassword(c.getPassword());

            return buildModifyCompteResponse(this.compteRepository.save(c1));
        }
    }

    /**
     *
     * @param c
     * @return
     *
     * Fonction utilisée dans updateCompte qui retourne un objet de
     * type ModifyCompteResponse que va retourner updateCompte afin de donner
     * à l'utilisateur les informations demandées.
     *
     */
    private ModifyCompteResponse buildModifyCompteResponse(Compte c) {
        return ModifyCompteResponse.builder()
                .email(c.getEmail())
                .adresse(c.getAdresse())
                .nom(c.getNom())
                .prenom(c.getPrenom())
                .password(c.getPassword())
                .build();
    }

    /**
     *
     * @param code
     * @return
     * @throws NoSuchAlgorithmException
     *
     * Fonction qui permet de hashes une chaine de caractère.
     * Ici, elle permet donc de protéger le mot de passe des comptes des clients.
     *
     */
    private String hashMdp(String code) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(code.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
}
