package fr.serveurregistrecomposants.repository;

import fr.serveurregistrecomposants.commun.CarteMere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteMereRepository extends JpaRepository<CarteMere, Integer> {
}