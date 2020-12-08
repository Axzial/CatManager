package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.cat.CatWithOwnerIdDto;
import fr.axzial.catmanager.dto.cat.CatReturnDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.service.CatService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@link Cat} controller.
 */
@RestController
@RequestMapping(value = "/cat")
public class CatController {

    private final CatService catService;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Instantiates a new Cat controller.
     *
     * @param catService the cat service
     */
    public CatController(CatService catService) {
        this.catService = catService;
    }

    /**
     * Get all {@link Cat}
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<CatReturnDto>> getCats() {
        List<CatReturnDto> catList = catService.findAllSimple();
        return new ResponseEntity<>(catList, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Get a single {@link Cat}
     *
     * @param id the id of the {@link Cat}
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<CatReturnDto> getCat(@PathVariable long id) {
        return catService.findByIdSimple(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatNotFoundException("Can't find Cat with id: " + id));
    }

    /**
     * Add a new {@link Cat}
     *
     * @param catWithOwnerIdDto the {@link Cat} with owner id dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<CatReturnDto> addCat(@RequestBody CatWithOwnerIdDto catWithOwnerIdDto) {
        return new ResponseEntity<>(catService.saveWithOwnerIdDto(catWithOwnerIdDto), HttpStatus.CREATED);
    }

    /**
     * Update a {@link Cat}
     *
     * @param id                the id of the {@link Cat}
     * @param catWithOwnerIdDto the {@link CatWithOwnerIdDto}
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CatReturnDto> update(@PathVariable long id, @RequestBody CatWithOwnerIdDto catWithOwnerIdDto) {
        return catService.updateWithOwnerIdDto(id, catWithOwnerIdDto).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatNotFoundException("Can't find Cat with id: " + id));
    }

    /**
     * Delete a {@link Cat}.
     *
     * @param id the id of the {@link Cat}
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        catService.delete(id);
    }
}
