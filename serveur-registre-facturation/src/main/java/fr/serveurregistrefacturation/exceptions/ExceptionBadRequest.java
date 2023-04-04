package fr.serveurregistrefacturation.exceptions;

/**
 * Classe servant à déclarer une erreur 400 durant l'exécution d'une requête
 */
public class ExceptionBadRequest extends Exception{
    public ExceptionBadRequest (String s) {
        super(s) ;
    }
}
