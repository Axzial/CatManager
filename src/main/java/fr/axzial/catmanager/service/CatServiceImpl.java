package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatDto;
import fr.axzial.catmanager.dto.CatWithOwnerIdDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.repository.CatOwnerRepository;
import fr.axzial.catmanager.repository.CatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CatServiceImpl(CatRepository catRepository, CatOwnerRepository catOwnerRepository) {
        this.catRepository = catRepository;
        this.catOwnerRepository = catOwnerRepository;
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
    public Cat save(CatDto catDto) {
        Cat cat = modelMapper.map(catDto, Cat.class);
        return catRepository.save(cat);
    }

    @Override
    public Cat saveWithOwnerIdDto(CatWithOwnerIdDto catWithOwnerIdDto) {
        Cat cat = new Cat();
        cat.setName(catWithOwnerIdDto.getName());
        cat.setColor(catWithOwnerIdDto.getColor());
        cat.setCatBreed(catWithOwnerIdDto.getCatBreed());

        Optional<CatOwner> optionalCatOwner= catOwnerRepository.findById(catWithOwnerIdDto.getOwnerId());

        if (optionalCatOwner.isEmpty()) cat.setOwner(null);
        else{
            cat.setOwner(optionalCatOwner.get());
        }

        return catRepository.save(cat);
    }

    @Override
    public Optional<Cat> update(long id, CatDto catDto) throws CatNotFoundException {
        if (findById(id).isPresent()){
            Cat cat = modelMapper.map(catDto, Cat.class);
            cat.setId(id);
            Cat savedCat = catRepository.save(cat);
            return Optional.of(savedCat);
        } else throw new CatNotFoundException("Can't find Cat with id: " + id);
    }

    @Override
    public void delete(long id) throws CatNotFoundException {
        if (findById(id).isPresent()){
            catRepository.deleteById(id);
        } else throw new CatNotFoundException("Can't find Cat with id: " + id);
    }
}
