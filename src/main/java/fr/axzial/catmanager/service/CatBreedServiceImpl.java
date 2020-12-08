package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.catbreed.CatBreedDto;
import fr.axzial.catmanager.exception.CatBreedNotFoundException;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.repository.CatBreedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The {@link CatBreed} service implementation.
 */
@Service
@Transactional
public class CatBreedServiceImpl implements CatBreedService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final CatBreedRepository catBreedRepository;

    /**
     * Instantiates a new Cat breed service.
     *
     * @param CatBreedRepository the {@link CatBreedRepository}
     */
    public CatBreedServiceImpl(CatBreedRepository CatBreedRepository) {
        this.catBreedRepository = CatBreedRepository;
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
    public Optional<CatBreed> update(long id, CatBreedDto catBreedDto) {
        if (catBreedRepository.findById(id).isPresent()) {
            CatBreed catBreed = modelMapper.map(catBreedDto, CatBreed.class);
            catBreed.setId(id);
            CatBreed savedCatBreed = catBreedRepository.save(catBreed);
            return Optional.of(savedCatBreed);
        } else throw new CatBreedNotFoundException("Can't find Breed with id: " + id);
    }

    @Override
    public void deleteById(long id) {
        if (catBreedRepository.findById(id).isPresent()) catBreedRepository.deleteById(id);
        else throw new CatBreedNotFoundException("Can't find Breed with id: " + id);
    }

}
