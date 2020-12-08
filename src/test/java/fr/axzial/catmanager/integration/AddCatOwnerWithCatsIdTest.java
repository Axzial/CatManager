package fr.axzial.catmanager.integration;

import com.google.gson.Gson;
import fr.axzial.catmanager.dto.catowner.CatOwnerWithCatsIdDto;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.repository.CatOwnerRepository;
import fr.axzial.catmanager.repository.CatRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddCatOwnerWithCatsIdTest {

    private final static String ENDPOINT_URL = "/catowner";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CatOwnerRepository catOwnerRepository;

    @Autowired
    private CatRepository catRepository;

    Gson gson = new Gson();

    private static List<Cat> catList;

    @BeforeAll
    void setUp(){
        catList = new ArrayList<>();
        catList.add(new Cat(1, "Jean"));
        catList.add(new Cat(2, "Patrick"));
        catList.add(new Cat(3, "Elvira"));
        catList.forEach(e -> catRepository.save(e));
    }

    @Test
    void addCatOwnerWithCatsId() throws Exception {
        CatOwnerWithCatsIdDto catOwnerWithCatsIdDto = new CatOwnerWithCatsIdDto();
        catOwnerWithCatsIdDto.setName("Axzial");
        catOwnerWithCatsIdDto.setCatList(Arrays.asList(1L, 3L));
        System.out.println(catRepository.findAll());
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(catOwnerWithCatsIdDto))
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        Optional<CatOwner> catOwner = catOwnerRepository.findById(1L);
        if (catOwner.isPresent()){
            if (catOwner.get().getCatList().get(0).getName().equalsIgnoreCase("Jean")){
                assert true;
            } else assert false;
        } else assert false;

    }

}
