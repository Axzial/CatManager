package fr.axzial.catmanager.controller;

import com.google.gson.Gson;
import fr.axzial.catmanager.dto.cat.CatDto;
import fr.axzial.catmanager.dto.cat.CatReturnDto;
import fr.axzial.catmanager.dto.cat.CatWithOwnerIdDto;
import fr.axzial.catmanager.dto.catbreed.CatBreedDto;
import fr.axzial.catmanager.model.Cat;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.service.CatBreedService;
import fr.axzial.catmanager.service.CatOwnerService;
import fr.axzial.catmanager.service.CatService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CatController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CatControllerTest {

    private static final String ENDPOINT_URL = "/cat";

    @MockBean
    private CatService catService;
    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    private static List<Cat> catList;
    private static List<CatReturnDto> catReturnDtoList;

    @BeforeAll
    void setUp() {
        CatController catController = new CatController(catService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(catController).build();

        catList = new ArrayList<>();
        catList.add(new Cat(1, "Lynx"));
        catList.add(new Cat(2, "Félin"));
        catList.add(new Cat(3, "Savanah"));

        catReturnDtoList = new ArrayList<>();
        catReturnDtoList.add(new CatReturnDto());
        catReturnDtoList.add(new CatReturnDto());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCats() throws Exception {
        Mockito.when(catService.findAllSimple()).thenReturn(catReturnDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCat() throws Exception {
        Mockito.when(catService.findByIdSimple(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(catReturnDtoList.get(0)));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catService, Mockito.times(1)).findByIdSimple(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(catService);
    }

    @Test
    void addCat() throws Exception {
        Mockito.when(catService.saveWithOwnerIdDto(ArgumentMatchers.any(CatWithOwnerIdDto.class))).thenReturn(new CatReturnDto());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(new CatWithOwnerIdDto()))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(catService, Mockito.times(1)).saveWithOwnerIdDto(ArgumentMatchers.any(CatWithOwnerIdDto.class));
        Mockito.verifyNoMoreInteractions(catService);
    }

    @Test
    void update() throws Exception {
        Mockito.when(catService.updateWithOwnerIdDto(1, new CatWithOwnerIdDto())).thenReturn(java.util.Optional.of(new CatReturnDto()));

        mockMvc.perform(MockMvcRequestBuilders.patch(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(new CatWithOwnerIdDto()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catService, Mockito.times(1)).updateWithOwnerIdDto(ArgumentMatchers.anyLong(), ArgumentMatchers.any(CatWithOwnerIdDto.class));
        Mockito.verifyNoMoreInteractions(catService);
    }

    @Test
    void delete() throws Exception {
        Mockito.doNothing().when(catService).delete(1);

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catService, Mockito.times(1)).delete(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(catService);
    }
}