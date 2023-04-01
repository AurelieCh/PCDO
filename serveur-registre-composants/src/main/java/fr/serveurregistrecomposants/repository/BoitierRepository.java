package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Boitier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoitierRepository extends JpaRepository<Boitier, Integer> {
}