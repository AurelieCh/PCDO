package fr.serveurregistrefacturation.exceptions;

/**
 * Classe servant à déclarer une erreur 404 durant l'exécution d'une requête
 */
public class ExceptionNotFound extends Exception{
    public ExceptionNotFound (String excep) {
        super(excep);
    }
}

