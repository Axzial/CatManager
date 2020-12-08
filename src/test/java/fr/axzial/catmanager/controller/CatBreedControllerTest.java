package fr.axzial.catmanager.controller;

import com.google.gson.Gson;
import fr.axzial.catmanager.dto.catbreed.CatBreedDto;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.service.CatBreedService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CatBreedController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CatBreedControllerTest {

    private static final String ENDPOINT_URL = "/catbreed";

    private CatBreedController catBreedController;
    @MockBean
    private CatBreedService catBreedService;
    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    private static List<CatBreed> catBreedList;

    @BeforeAll
    void setUp() {
        this.catBreedController = new CatBreedController(catBreedService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.catBreedController).build();
        catBreedList = new ArrayList<>();
        catBreedList.add(new CatBreed(1, "Lynx"));
        catBreedList.add(new CatBreed(2, "Félin"));
        catBreedList.add(new CatBreed(3, "Savanah"));

    }

    @AfterAll
    void tearDown() {
    }


    @Test
    void getBreeds() throws Exception {
        Mockito.when(catBreedService.findAll()).thenReturn(catBreedList);

        System.out.println(catBreedService.findAll());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBreed() throws Exception {
        Mockito.when(catBreedService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(catBreedList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catBreedService, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(catBreedService);
    }

    @Test
    void addBreed() throws Exception {
        Mockito.when(catBreedService.save(ArgumentMatchers.any(CatBreedDto.class))).thenReturn(new CatBreed());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(new CatBreedDto()))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(catBreedService, Mockito.times(1)).save(ArgumentMatchers.any(CatBreedDto.class));
        Mockito.verifyNoMoreInteractions(catBreedService);
    }

    @Test
    void update() throws Exception {
        Mockito.when(catBreedService.update(1, new CatBreedDto())).thenReturn(java.util.Optional.of(new CatBreed()));

        mockMvc.perform(MockMvcRequestBuilders.patch(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(new CatBreed()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catBreedService, Mockito.times(1)).update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(CatBreedDto.class));
        Mockito.verifyNoMoreInteractions(catBreedService);
    }

    @Test
    void delete() throws Exception {
        Mockito.doNothing().when(catBreedService).deleteById(1);

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catBreedService, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(catBreedService);
    }
}