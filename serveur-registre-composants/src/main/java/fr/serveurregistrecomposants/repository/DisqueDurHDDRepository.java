package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.DisqueDurHDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisqueDurHDDRepository extends JpaRepository<DisqueDurHDD, Integer> {
}