package fr.serveurregistrefacturation.commun;

<<<<<<< Updated upstream
import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
=======
<<<<<<< Updated upstream
import lombok.*;

import javax.persistence.*;
=======
import fr.serveurregistrefacturation.commun.types.TypePaiement;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
>>>>>>> Stashed changes
>>>>>>> Stashed changes

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Facturation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
<<<<<<< Updated upstream
    private Long id;
    private String email;
<<<<<<< Updated upstream
=======
=======
    private Integer id;
    private String email;
>>>>>>> Stashed changes
    private String adresse;
    private Date dateCreation;
    private Integer commande;
    private TypePaiement typePaiement;
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
}
