package fr.axzial.catmanager.service;

import fr.axzial.catmanager.dto.catowner.CatOwnerDto;
import fr.axzial.catmanager.dto.catowner.CatOwnerWithCatsIdDto;
import fr.axzial.catmanager.dto.catowner.CatOwnerReturnDto;
import fr.axzial.catmanager.exception.CatNotFoundException;
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

/**
 * The {@link CatOwner} service implementation.
 */
@Service
@Transactional
public class CatOwnerServiceImpl implements CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;
    private final CatRepository catRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Instantiates a new Cat owner service.
     *
     * @param catOwnerRepository the {@link CatOwnerRepository}
     * @param catRepository      the {@link CatRepository}
     */
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
        for (CatOwner catOwner : catList) {
            catSimpleDtoList.add(modelMapper.map(catOwner, CatOwnerReturnDto.class));
        }
        return catSimpleDtoList;
    }

    @Override
    public Optional<CatOwner> findById(long id) {
        return catOwnerRepository.findById(id);
    }

    @Override
    public Optional<CatOwnerReturnDto> findByIdSimple(long id) {
        if (catOwnerRepository.findById(id).isEmpty()) return Optional.empty();
        return Optional.of(modelMapper.map(catOwnerRepository.getOne(id), CatOwnerReturnDto.class));
    }

    @Override
    public CatOwner save(CatOwnerDto catOwnerDto) {
        CatOwner catOwner = modelMapper.map(catOwnerDto, CatOwner.class);
        catOwnerRepository.save(catOwner);
        if (!catOwner.getCatList().isEmpty()) {
            catOwner.getCatList().stream().peek(e -> e.setOwner(catOwner)).forEach(catRepository::save);
        }
        return catOwnerRepository.save(catOwner);
    }

    @Override
    public CatOwnerReturnDto saveWithCatsId(CatOwnerWithCatsIdDto catOwnerWithCatsIdDto) {
        CatOwner catOwner = new CatOwner();
        catOwner.setName(catOwnerWithCatsIdDto.getName());

        //Cats
        if (catOwnerWithCatsIdDto.getCatList() != null) {

            catOwnerWithCatsIdDto.getCatList().forEach(e -> {

                Optional<Cat> optionalCat = catRepository.findById(e);

                if (optionalCat.isEmpty()) {
                    throw new CatNotFoundException();
                }
                Cat cat = optionalCat.get();
                cat.setOwner(catOwner);
                catOwner.getCatList().add(cat);
                catRepository.save(cat);
            });

        }

        return modelMapper.map(catOwnerRepository.save(catOwner), CatOwnerReturnDto.class);
    }

    @Override
    public Optional<CatOwner> update(long id, CatOwnerDto catOwnerDto) {
        if (findById(id).isPresent()) {
            CatOwner catOwner = modelMapper.map(catOwnerDto, CatOwner.class);
            catOwner.setId(id);
            CatOwner savedCatOwner = catOwnerRepository.save(catOwner);
            return Optional.of(savedCatOwner);
        } else throw new CatOwnerNotFoundException("Can't find CatOwner with id: " + id);
    }

    @Override
    public Optional<CatOwnerReturnDto> updateWithCatsId(long id, CatOwnerWithCatsIdDto catOwnerWithCatsIdDto) {
        if (findById(id).isPresent()) {

            CatOwner catOwner = new CatOwner();
            catOwner.setName(catOwnerWithCatsIdDto.getName());
            catOwner.setId(id);

            //Cats
            if (catOwnerWithCatsIdDto.getCatList() != null) {

                catOwnerWithCatsIdDto.getCatList().forEach(e -> {

                    Optional<Cat> optionalCat = catRepository.findById(e);

                    if (optionalCat.isEmpty()) {
                        throw new CatOwnerNotFoundException();
                    }
                    Cat cat = optionalCat.get();
                    cat.setOwner(catOwner);
                    catOwner.getCatList().add(cat);
                    catRepository.save(cat);
                });
            }
            return Optional.of(modelMapper.map(catOwnerRepository.save(catOwner), CatOwnerReturnDto.class));
        } else throw new CatOwnerNotFoundException("Can't find CatOwner with id: " + id);


    }

    @Override
    public void delete(long id) {
        if (catOwnerRepository.findById(id).isPresent()) {
            catOwnerRepository.findById(id).get().getCatList().forEach(e -> e.setOwner(null));
            catOwnerRepository.deleteById(id);
        } else throw new CatOwnerNotFoundException("Can't find CatOwner with id: " + id);
    }
}
