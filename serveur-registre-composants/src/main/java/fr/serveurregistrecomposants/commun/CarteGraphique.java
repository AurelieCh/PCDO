package fr.serveurregistrecomposants.commun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CPU")
public class CarteGraphique extends Composant{
    private Integer nbVentilateurs;
    private Double frequence;
    private Integer vRam;

}
