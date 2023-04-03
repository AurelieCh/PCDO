package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Caracteristique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristiqueRepository extends JpaRepository<Caracteristique, Integer> {
}