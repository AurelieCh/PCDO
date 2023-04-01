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
    private List<GetAlimResponse> alimentations;
    private List<GetBoitierResponse> boitiers;
    private List<GetCarteMereResponse> cartemeres;
    private List<GetDisqueDurHDDResponse> hdd;
    private List<GetDisqueDurSSDResponse> ssd;
    private List<GetMemoireResponse> ram;

    public GetComposantsResponse(){
        this.processeurs = new ArrayList<>();
        this.cartesgraphiques = new ArrayList<>();
        this.alimentations = new ArrayList<>();
        this.boitiers = new ArrayList<>();
        this.cartemeres = new ArrayList<>();
        this.hdd = new ArrayList<>();
        this.ssd = new ArrayList<>();
        this.ram = new ArrayList<>();
    }
}
