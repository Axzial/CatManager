package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.CatOwnerDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.repository.CatOwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatOwnerServiceImpl implements CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CatOwnerServiceImpl(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    @Override
    public List<CatOwner> findAll() {
        return catOwnerRepository.findAll();
    }

    @Override
    public Optional<CatOwner> findById(long id) {
        return catOwnerRepository.findById(id);
    }

    @Override
    public CatOwner save(CatOwnerDto catOwnerDto) {
        CatOwner catOwner = modelMapper.map(catOwnerDto, CatOwner.class);
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public Optional<CatOwner> update(long id, CatOwnerDto catOwnerDto) throws BreedNotFoundException {
        if (findById(id).isPresent()){
            CatOwner catOwner = modelMapper.map(catOwnerDto, CatOwner.class);
            CatOwner savedCatOwner = catOwnerRepository.save(catOwner);
            return Optional.of(savedCatOwner);
        } else throw new CatOwnerNotFoundException("Can't find CatOwner with id: " + id);
    }

    @Override
    public void delete(long id) throws BreedNotFoundException {
        if (findById(id).isPresent()){
            catOwnerRepository.deleteById(id);
        } else throw new CatOwnerNotFoundException("Can't find CatOwner with id: " + id);
    }
}
