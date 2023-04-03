package fr.serveurregistrecommandes.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Classe servant le retour d'un message d'erreur type 400 ou 404
 * à l'utilisateur.
 */
public class ErrorMessage {
    private String message;
}
