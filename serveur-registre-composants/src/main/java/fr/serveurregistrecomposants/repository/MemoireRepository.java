package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.Memoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoireRepository extends JpaRepository<Memoire, Integer> {
}