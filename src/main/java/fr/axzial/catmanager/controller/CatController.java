package fr.axzial.catmanager.controller;

import fr.axzial.catmanager.dto.CatBreedDto;
import fr.axzial.catmanager.dto.CatDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.service.CatService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cat")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<Cat>> getCats(){
        List<Cat> catList = catService.findAll();
        return new ResponseEntity<>(catList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCat(@PathVariable long id){
        return catService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatNotFoundException("Can't find Cat with id: " + id));
    }

    @PutMapping
    public ResponseEntity<Cat> addCat(@RequestBody CatDto catDto){
        return new ResponseEntity<>(catService.save(catDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cat> update(@PathVariable long id, @RequestBody CatDto catDto){
        return catService.update(id, catDto).map(ResponseEntity::ok)
                .orElseThrow(() -> new CatNotFoundException("Can't find Cat with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        catService.delete(id);
    }
}
