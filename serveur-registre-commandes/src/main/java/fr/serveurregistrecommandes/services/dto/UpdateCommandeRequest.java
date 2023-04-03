package fr.serveurregistrecommandes.services.dto;

import fr.serveurregistrecommandes.commun.types.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateCommandeRequest {
    private Integer id;
    private Status status;
}
