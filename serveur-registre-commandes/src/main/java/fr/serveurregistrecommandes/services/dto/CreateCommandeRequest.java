package fr.serveurregistrecommandes.services.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateCommandeRequest {
    private List<Integer> composants;
    private String email;
    private String adresse;
}
