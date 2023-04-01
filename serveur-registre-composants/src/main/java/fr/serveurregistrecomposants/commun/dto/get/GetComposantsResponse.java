package fr.serveurregistrecomposants.commun.dto.get;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class GetComposantsResponse {
    private List<GetCPUResponse> processeurs;
    private List<GetGPUResponse> cartesgraphiques;

    public GetComposantsResponse(){
        this.processeurs = new ArrayList<>();
        this.cartesgraphiques = new ArrayList<>();
    }
}
