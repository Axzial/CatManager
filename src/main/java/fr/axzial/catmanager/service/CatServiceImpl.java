package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatDto;
import fr.axzial.catmanager.dto.CatWithOwnerIdDto;
import fr.axzial.catmanager.dto.CatReturnDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.repository.CatBreedRepository;
import fr.axzial.catmanager.repository.CatOwnerRepository;
import fr.axzial.catmanager.repository.CatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@link Cat} service implementation.
 */
@Service
@Transactional
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final CatBreedRepository catBreedRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Instantiates a new Cat service.
     *
     * @param catRepository      the {@link CatRepository}
     * @param catOwnerRepository the {@link CatOwnerRepository}
     * @param catBreedRepository the {@link CatBreedRepository}
     */
    public CatServiceImpl(CatRepository catRepository, CatOwnerRepository catOwnerRepository, CatBreedRepository catBreedRepository) {
        this.catRepository = catRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catBreedRepository = catBreedRepository;
    }

    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    @Override
    public Optional<Cat> findById(long id) {
        return catRepository.findById(id);
    }

    @Override
    public Optional<CatReturnDto> findByIdSimple(long id) {
        if (catRepository.findById(id).isEmpty()) return Optional.empty();
        return Optional.of(modelMapper.map(catRepository.getOne(id), CatReturnDto.class));
    }

    @Override
    public List<CatReturnDto> findAllSimple() {
        List<Cat> catList = catRepository.findAll();
        List<CatReturnDto> catReturnDtoList = new ArrayList<>();
        for (Cat cat : catList) {
            CatReturnDto catReturnDto = modelMapper.map(cat, CatReturnDto.class);
            catReturnDtoList.add(catReturnDto);
        }
        System.out.println(catReturnDtoList);
        return catReturnDtoList;
    }

    @Override
    public Cat save(CatDto catDto) {
        Cat cat = modelMapper.map(catDto, Cat.class);
        return catRepository.save(cat);
    }

    @Override
    public CatReturnDto saveWithOwnerIdDto(CatWithOwnerIdDto catWithOwnerIdDto) {
        Cat cat = new Cat();
        cat.setName(catWithOwnerIdDto.getName());
        cat.setColor(catWithOwnerIdDto.getColor());
        cat.setId(catWithOwnerIdDto.getId());

        //Breed
        Optional<CatBreed> optionalCatBreed = catBreedRepository.findById(catWithOwnerIdDto.getCatBreedId());
        if (optionalCatBreed.isEmpty()) cat.setCatBreed(null);
        else {
            cat.setCatBreed(optionalCatBreed.get());
        }

        //Owner
        Optional<CatOwner> optionalCatOwner = catOwnerRepository.findById(catWithOwnerIdDto.getOwnerId());
        if (optionalCatOwner.isEmpty()) cat.setOwner(null);
        else {
            CatOwner catOwner = optionalCatOwner.get();
            cat.setOwner(catOwner);
            catOwner.addCat(cat);
        }

        return modelMapper.map(catRepository.save(cat), CatReturnDto.class);
    }

    @Override
    public Optional<Cat> update(long id, CatDto catDto) throws CatNotFoundException {
        if (findById(id).isPresent()) {
            Cat cat = modelMapper.map(catDto, Cat.class);
            cat.setId(id);
            Cat savedCat = catRepository.save(cat);
            return Optional.of(savedCat);
        } else throw new CatNotFoundException("Can't find Cat with id: " + id);
    }

    @Override
    public Optional<CatReturnDto> updateWithOwnerIdDto(long id, CatWithOwnerIdDto catWithOwnerIdDto) {
        Cat cat = new Cat();
        cat.setName(catWithOwnerIdDto.getName());
        cat.setColor(catWithOwnerIdDto.getColor());
        cat.setId(catWithOwnerIdDto.getId());

        //Breed
        Optional<CatBreed> optionalCatBreed = catBreedRepository.findById(catWithOwnerIdDto.getCatBreedId());
        if (optionalCatBreed.isEmpty()) cat.setCatBreed(null);
        else {
            cat.setCatBreed(optionalCatBreed.get());
        }

        //Owner
        Optional<CatOwner> optionalCatOwner = catOwnerRepository.findById(catWithOwnerIdDto.getOwnerId());
        if (optionalCatOwner.isEmpty()) cat.setOwner(null);
        else {
            CatOwner catOwner = optionalCatOwner.get();
            catOwner.addCat(cat);
            cat.setOwner(catOwner);
            catRepository.save(cat);
        }

        if (findById(id).isPresent()) {
            catWithOwnerIdDto.setId(id);
            return Optional.ofNullable(saveWithOwnerIdDto(catWithOwnerIdDto));
        } else throw new CatNotFoundException("Can't find Cat with id: " + id);
    }

    @Override
    public void delete(long id) throws CatNotFoundException {
        if (findById(id).isPresent()) {
            catRepository.deleteById(id);
        } else throw new CatNotFoundException("Can't find Cat with id: " + id);
    }
}
