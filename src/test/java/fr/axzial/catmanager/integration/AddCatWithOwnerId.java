package fr.axzial.catmanager.integration;

import com.google.gson.Gson;
import fr.axzial.catmanager.dto.cat.CatWithOwnerIdDto;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.repository.CatOwnerRepository;
import fr.axzial.catmanager.repository.CatRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddCatWithOwnerId {

    private final static String ENDPOINT_URL = "/cat";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CatOwnerRepository catOwnerRepository;

    @Autowired
    private CatRepository catRepository;

    Gson gson = new Gson();

    private static List<CatOwner> catOwnerList;

    @BeforeAll
    void setUp(){
        catOwnerList = new ArrayList<>();
        catOwnerList.add(new CatOwner(1, "Axzial"));
        catOwnerList.add(new CatOwner(2, "Mohul_"));
        catOwnerList.add(new CatOwner(3, "NextSap"));
        catOwnerList.forEach(e -> catOwnerRepository.save(e));
    }

    @Test
    void addCatWithOwnerId() throws Exception {
        CatWithOwnerIdDto catWithOwnerIdDto = new CatWithOwnerIdDto();
        catWithOwnerIdDto.setName("Pandoche");
        catWithOwnerIdDto.setOwnerId(2);

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(catWithOwnerIdDto))
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        Optional<Cat> cat = catRepository.findById(1L);
        if (cat.isPresent()){
            if (cat.get().getOwner().getId() == 2){
                assert true;
            }else assert false;
        }else assert false;
    }
}
