package fr.serveurregistrecomposants.commun.dto.get;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetComposantsResponse {
    private List<GetCPUResponse> processeurs;
    private List<GetGPUResponse> cartesgraphiques;
}
