package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.entity.CatOwnerDto;
import fr.axzial.catmanager.dto.requestbody.CatOwnerWithCatsIdDto;
import fr.axzial.catmanager.dto.returnbody.CatOwnerReturnDto;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.CatOwner;

import java.util.List;
import java.util.Optional;

/**
 * The {@link CatOwner} service interface.
 */
public interface CatOwnerService {

    /**
     * Find all {@link CatOwner}
     *
     * @return the list of {@link CatOwner}
     */
    List<CatOwner> findAll();

    /**
     * Find all {@link fr.axzial.catmanager.dto.returnbody.CatOwnerSimpleDto}.
     *
     * @return the list
     */
    List<CatOwnerReturnDto> findAllSimple();

    /**
     * Find by id {@link CatOwner}.
     *
     * @param id the id of the {@link CatOwner}
     * @return the optional {@link CatOwner}
     */
    Optional<CatOwner> findById(long id);

    /**
     * Save a new {@link CatOwner}.
     *
     * @param catOwnerDto the {@link CatOwnerDto}
     * @return the {@link CatOwner}
     */
    CatOwner save(CatOwnerDto catOwnerDto);

    /**
     * Save a new {@link CatOwner} with {@link CatOwnerWithCatsIdDto} and get a {@link CatOwnerReturnDto}.
     * Used to assign multiple {@link fr.axzial.catmanager.model.Cat} to a {@link CatOwner}
     *
     * @param catOwnerWithCatsIdDto the {@link CatOwnerWithCatsIdDto}
     * @return the {@link CatOwnerReturnDto}
     */
    CatOwnerReturnDto saveWithCatsId(CatOwnerWithCatsIdDto catOwnerWithCatsIdDto);

    /**
     * Update a {@link CatOwner}.
     *
     * @param id          the id of the {@link CatOwner}
     * @param catOwnerDto the {@link CatOwnerDto}
     * @return the optional {@link CatOwner}
     * @throws CatOwnerNotFoundException the {@link CatOwnerNotFoundException}
     */
    Optional<CatOwner> update(long id, CatOwnerDto catOwnerDto) throws CatOwnerNotFoundException;

    Optional<CatOwnerReturnDto> updateWithCatsId(long id, CatOwnerWithCatsIdDto catOwnerWithCatsIdDto);

    /**
     * Delete a {@link CatOwner}.
     *
     * @param id the id of the {@link CatOwner}
     * @throws CatOwnerNotFoundException the {@link CatOwnerNotFoundException}
     */
    void delete(long id) throws CatOwnerNotFoundException;
}
