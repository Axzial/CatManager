package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.entity.CatOwnerDto;
import fr.axzial.catmanager.dto.requestbody.CatOwnerWithCatsId;
import fr.axzial.catmanager.dto.returnbody.CatOwnerReturnDto;
import fr.axzial.catmanager.dto.returnbody.CatOwnerSimpleDto;
import fr.axzial.catmanager.dto.returnbody.CatSimpleDto;
import fr.axzial.catmanager.exception.BreedNotFoundException;
import fr.axzial.catmanager.exception.CatOwnerNotFoundException;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.repository.CatOwnerRepository;
import fr.axzial.catmanager.repository.CatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatOwnerServiceImpl implements CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;
    private final CatRepository catRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CatOwnerServiceImpl(CatOwnerRepository catOwnerRepository, CatRepository catRepository) {
        this.catOwnerRepository = catOwnerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public List<CatOwner> findAll() {
        return catOwnerRepository.findAll();
    }

    @Override
    public List<CatOwnerReturnDto> findAllSimple() {
        List<CatOwner> catList = catOwnerRepository.findAll();
        List<CatOwnerReturnDto> catSimpleDtoList = new ArrayList<>();
        for (CatOwner catOwner : catList){
            catSimpleDtoList.add(modelMapper.map(catOwner, CatOwnerReturnDto.class));
        }
        return catSimpleDtoList;
    }

    @Override
    public Optional<CatOwner> findById(long id) {
        return catOwnerRepository.findById(id);
    }

    @Override
    public CatOwner save(CatOwnerDto catOwnerDto) {
        CatOwner catOwner = modelMapper.map(catOwnerDto, CatOwner.class);
        catOwnerRepository.save(catOwner);
        if (!catOwner.getCatList().isEmpty()){
            catOwner.getCatList().stream().peek(e -> e.setOwner(catOwner)).forEach(catRepository::save);
        }
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public CatOwnerReturnDto saveWithCatsId(CatOwnerWithCatsId catOwnerWithCatsId) {
        CatOwner catOwner = new CatOwner();
        catOwner.setName(catOwnerWithCatsId.getName());
        if (catOwnerWithCatsId.getCatList() != null){
            catOwnerWithCatsId.getCatList().forEach(e -> {
                Optional<Cat> optionalCat = catRepository.findById(e);
                if (optionalCat.isPresent()){
                    Cat cat = optionalCat.get();
                    cat.setOwner(catOwner);
                    catOwner.getCatList().add(cat);
                    catRepository.save(cat);

                }
            });
        }
        return modelMapper.map(catOwnerRepository.save(catOwner), CatOwnerReturnDto.class);
    }

    @Override
    public Optional<CatOwner> update(long id, CatOwnerDto catOwnerDto) throws BreedNotFoundException {
        if (findById(id).isPresent()){
            CatOwner catOwner = modelMapper.map(catOwnerDto, CatOwner.class);
            catOwner.setId(id);
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
