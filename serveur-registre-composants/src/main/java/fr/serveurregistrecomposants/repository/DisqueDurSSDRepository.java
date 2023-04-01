package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.DisqueDurSSD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisqueDurSSDRepository extends JpaRepository<DisqueDurSSD, Integer> {
}