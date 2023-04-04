package fr.serveurregistrecompte.commun.exceptions;

/**
 * Classe servant à déclarer une erreur 400 durant l'éxécution d'une requête
 */
public class ExceptionBadRequest extends Exception{
    public ExceptionBadRequest (String s) {
        super(s) ;
    }
}
