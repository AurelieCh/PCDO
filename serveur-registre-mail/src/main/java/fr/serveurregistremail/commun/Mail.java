package fr.serveurregistremail.commun;

import fr.serveurregistremail.commun.types.TypeMail;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Mail implements Serializable {
    private TypeMail typeMail;
    private String emailDesti;
    private Integer commande;
}
