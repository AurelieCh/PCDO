package fr.serveurregistreautobuild.commun;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Integer cpu;
    private Integer gpu;
    private Integer ssd;
    private Integer hdd;
    private Integer boitier;
    private Integer alim;
    private Integer carteMere;
    private Integer ram;
}
