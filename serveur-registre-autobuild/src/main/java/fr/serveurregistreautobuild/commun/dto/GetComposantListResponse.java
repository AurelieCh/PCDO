package fr.serveurregistreautobuild.commun.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetComposantListResponse {
    private List<GetComposantResponse> composants;
}
