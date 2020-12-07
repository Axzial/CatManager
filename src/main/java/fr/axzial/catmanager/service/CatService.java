package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatDto;
import fr.axzial.catmanager.dto.CatWithOwnerIdDto;
import fr.axzial.catmanager.dto.CatReturnDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;

import java.util.List;
import java.util.Optional;

/**
 * The {@link Cat} service interface.
 */
public interface CatService {
    /**
     * Find all {@link Cat}
     *
     * @return the list of {@link Cat}
     */
    List<Cat> findAll();

    /**
     * Find a {@link Cat} by id
     *
     * @param id the id of the {@link Cat}
     * @return the optional {@link Cat}
     */
    Optional<Cat> findById(long id);

    /**
     * Save a new {@link Cat}.
     *
     * @param catDto the {@link CatDto}
     * @return the {@link Cat}
     */
    Cat save(CatDto catDto);

    /**
     * Save a new {@link Cat} with {@link CatWithOwnerIdDto} and get a {@link CatReturnDto}
     *
     * @param catWithOwnerIdDto the {@link CatWithOwnerIdDto}
     * @return the {@link CatReturnDto}
     */
    CatReturnDto saveWithOwnerIdDto(CatWithOwnerIdDto catWithOwnerIdDto);

    /**
     * Update a {@link Cat}.
     *
     * @param id     the id of the {@link Cat}
     * @param catDto the {@link CatDto}
     * @return the optional {@link Cat}
     * @throws CatNotFoundException the {@link CatNotFoundException}
     */
    Optional<Cat> update(long id, CatDto catDto) throws CatNotFoundException;

    /**
     * Delete a {@link Cat}.
     *
     * @param id the id of the {@link Cat}
     * @throws CatNotFoundException the {@link CatNotFoundException}
     */
    void delete(long id) throws CatNotFoundException;

    /**
     * Find all {@link Cat} as {@link CatReturnDto}.
     *
     * @return the list of {@link CatReturnDto}
     */
    List<CatReturnDto> findAllSimple();

    /**
     * Find a {@link Cat} as {@link CatReturnDto} by id.
     *
     * @param id the id of the {@link Cat}
     * @return the optional {@link CatReturnDto}
     */
    Optional<CatReturnDto> findByIdSimple(long id);

    /**
     * Update a {@link Cat} as {@link CatWithOwnerIdDto}.
     *
     * @param id                the id of the {@link Cat}
     * @param catWithOwnerIdDto the {@link CatWithOwnerIdDto}
     * @return the optional {@link CatReturnDto}
     */
    Optional<CatReturnDto> updateWithOwnerIdDto(long id, CatWithOwnerIdDto catWithOwnerIdDto);
}
