package fr.axzial.catmanager.repository;

import fr.axzial.catmanager.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link Cat} repository.
 */
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
