package fr.serveurregistrecompte.commun.exceptions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessageClientException {
    private String message;
}
