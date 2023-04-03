package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Composant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposantRepository extends JpaRepository<Composant, Integer> {
}