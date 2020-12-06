package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatBreedDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.repository.CatBreedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatBreedServiceImpl implements CatBreedService {

    private final CatBreedRepository catBreedRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CatBreedServiceImpl(CatBreedRepository catBreedRepository) {
        this.catBreedRepository = catBreedRepository;
    }

    @Override
    public List<CatBreed> findAll() {
        return catBreedRepository.findAll();
    }

    @Override
    public Optional<CatBreed> findById(long id) {
        return catBreedRepository.findById(id);
    }

    @Override
    public CatBreed save(CatBreedDto catBreedDto) {
        CatBreed catBreed = modelMapper.map(catBreedDto, CatBreed.class);
        return catBreedRepository.save(catBreed);
    }

    @Override
    public Optional<CatBreed> update(long id, CatBreedDto catBreedDto) throws BreedNotFoundException {
        if (findById(id).isPresent()){
            CatBreed catBreed = modelMapper.map(catBreedDto, CatBreed.class);
            catBreed.setId(id);
            CatBreed savedCatBreed = catBreedRepository.save(catBreed);
            return Optional.of(savedCatBreed);
        } else throw new BreedNotFoundException("Can't find Breed with id: " + id);
    }

    @Override
    public void delete(long id) throws BreedNotFoundException {
        if (findById(id).isPresent()){
            catBreedRepository.deleteById(id);
        } else throw new BreedNotFoundException("Can't find Breed with id: " + id);
    }
}
