package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.entity.CatOwnerDto;
import fr.axzial.catmanager.dto.requestbody.CatOwnerWithCatsIdDto;
import fr.axzial.catmanager.dto.returnbody.CatOwnerReturnDto;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.service.CatOwnerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@link CatOwner} controller.
 */
@RestController
@RequestMapping(value = "/catowner")
public class CatOwnerController {

    private final CatOwnerService catOwnerService;

    /**
     * Instantiates a new Cat owner controller.
     *
     * @param catOwnerService is the {@link CatOwnerService}
     */
    public CatOwnerController(CatOwnerService catOwnerService) {
        this.catOwnerService = catOwnerService;
    }

    /**
     * Get all {@link CatOwner}
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<CatOwnerReturnDto>> getOwners() {
        return new ResponseEntity<>(catOwnerService.findAllSimple(), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Get a single {@link CatOwner}
     *
     * @param id the id of the {@link CatOwner}
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<CatOwner> getOwner(@PathVariable long id) {
        return catOwnerService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatOwnerNotFoundException("Can't find CatOwner with id: " + id));
    }

    /**
     * Add a new {@link CatOwner}
     *
     * @param catOwnerWithCatsIdDto the {@link CatOwner} with cats id
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity<CatOwnerReturnDto> addCatOwner(@RequestBody CatOwnerWithCatsIdDto catOwnerWithCatsIdDto) {
        return new ResponseEntity<>(catOwnerService.saveWithCatsId(catOwnerWithCatsIdDto), HttpStatus.CREATED);
    }

    /**
     * Update a {@link CatOwner}
     *
     * @param id          the id of the {@link CatOwner}
     * @param catOwnerDto the cat owner dto
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CatOwnerReturnDto> update(@PathVariable long id, @RequestBody CatOwnerWithCatsIdDto catOwnerWithCatsIdDto) {
        return catOwnerService.updateWithCatsId(id, catOwnerWithCatsIdDto).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatOwnerNotFoundException("Can't find CatOwner with id: " + id));
    }

    /**
     * Delete a {@link CatOwner}.
     *
     * @param id the id of the {@link CatOwner}
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        catOwnerService.delete(id);
    }
}
