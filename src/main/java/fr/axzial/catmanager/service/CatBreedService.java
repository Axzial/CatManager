package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatBreedDto;
import fr.axzial.catmanager.exception.CatBreedNotFoundException;
import fr.axzial.catmanager.model.CatBreed;

import java.util.List;
import java.util.Optional;

/**
 * The {@link CatBreed} service interface.
 */
public interface CatBreedService {
    /**
     * Find all {@link CatBreed}.
     *
     * @return the list of {@link CatBreed}
     */
    List<CatBreed> findAll();

    /**
     * Find by id {@link CatBreed}.
     *
     * @param id the id of the {@link CatBreed}
     * @return the optional {@link CatBreed}
     */
    Optional<CatBreed> findById(long id);

    /**
     * Save {@link CatBreed}.
     *
     * @param catBreedDto the {@link CatBreedDto}
     * @return the {@link CatBreed}
     */
    CatBreed save(CatBreedDto catBreedDto);

    /**
     * Update {@link CatBreed}.
     *
     * @param id          the id of the {@link CatBreed}
     * @param catBreedDto the {@link CatBreedDto}
     * @return the optional {@link CatBreed}
     * @throws CatBreedNotFoundException the {@link CatBreedNotFoundException}
     */
    Optional<CatBreed> update(long id, CatBreedDto catBreedDto);

    /**
     * Delete {@link CatBreed}.
     *
     * @param id the id of the {@link CatBreed}
     * @throws CatBreedNotFoundException the {@link CatBreedNotFoundException}
     */
    void deleteById(long id);


}
