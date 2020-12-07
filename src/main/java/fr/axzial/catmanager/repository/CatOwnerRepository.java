package fr.axzial.catmanager.repository;

import fr.axzial.catmanager.model.CatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link CatOwner} repository.
 */
@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
}
