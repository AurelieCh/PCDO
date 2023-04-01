package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Alimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentationRepository extends JpaRepository<Alimentation, Integer> {
}