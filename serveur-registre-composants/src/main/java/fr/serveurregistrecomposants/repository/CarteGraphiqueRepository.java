package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.CarteGraphique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteGraphiqueRepository extends JpaRepository<CarteGraphique, Integer> {
}