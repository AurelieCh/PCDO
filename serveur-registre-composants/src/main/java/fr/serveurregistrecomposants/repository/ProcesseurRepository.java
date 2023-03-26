package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Processeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesseurRepository extends JpaRepository<Processeur, Integer> {
}