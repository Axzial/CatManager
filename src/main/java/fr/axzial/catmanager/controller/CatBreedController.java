package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.CatBreedDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.service.CatBreedService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The {@link CatBreed} controller.
 */
@RestController
@RequestMapping(value = "/catbreed")
public class CatBreedController {

    private final CatBreedService catBreedService;

    /**
     * Instantiates a new Cat breed controller.
     *
     * @param catBreedService the cat breed service
     */
    public CatBreedController(CatBreedService catBreedService) {
        this.catBreedService = catBreedService;
    }

    /**
     * Get all {@link CatBreed}
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<CatBreed>> getBreeds() {
        List<CatBreed> catBreeds = catBreedService.findAll();
        return new ResponseEntity<>(catBreeds, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Get a single {@link CatBreed}
     *
     * @param id the id of the {@link CatBreed}
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<CatBreed> getBreed(@PathVariable long id) {
        return catBreedService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new BreedNotFoundException("Can't find Breed with id: " + id));
    }

    /**
     * Add a new {@link CatBreed}
     *
     * @param catBreedDto the {@link CatBreed} dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<CatBreed> addBreed(@RequestBody CatBreedDto catBreedDto) {
        return new ResponseEntity<>(catBreedService.save(catBreedDto), HttpStatus.CREATED);
    }

    /**
     * Update a {@link CatBreed}
     *
     * @param id          the id of the {@link CatBreed}
     * @param catBreedDto the {@link CatBreed} dto
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CatBreed> update(@PathVariable long id, @RequestBody CatBreedDto catBreedDto) {
        return catBreedService.update(id, catBreedDto).map(ResponseEntity::ok)
                .orElseThrow(() -> new BreedNotFoundException("Can't find Breed with id: " + id));
    }

    /**
     * Delete a {@link CatBreed}.
     *
     * @param id the id of the {@link CatBreed}
     * @return
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        catBreedService.deleteById(id);
    }

}
