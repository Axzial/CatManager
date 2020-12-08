package fr.axzial.catmanager.controller;

import com.google.gson.Gson;
import fr.axzial.catmanager.dto.catbreed.CatBreedDto;
import fr.axzial.catmanager.dto.catowner.CatOwnerDto;
import fr.axzial.catmanager.dto.catowner.CatOwnerReturnDto;
import fr.axzial.catmanager.dto.catowner.CatOwnerWithCatsIdDto;
import fr.axzial.catmanager.model.CatBreed;
import fr.axzial.catmanager.model.CatOwner;
import fr.axzial.catmanager.service.CatBreedService;
import fr.axzial.catmanager.service.CatOwnerService;
import org.junit.jupiter.api.*;
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

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CatOwnerController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CatOwnerControllerTest {

    private static final String ENDPOINT_URL = "/catowner";

    private CatOwnerController catOwnerController;
    @MockBean
    private CatOwnerService catOwnerService;
    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    private static List<CatOwner> catOwnerList;
    private static List<CatOwnerReturnDto> catOwnerReturnDtoList;

    @BeforeAll
    void setUp() {
        this.catOwnerController = new CatOwnerController(catOwnerService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.catOwnerController).build();

        catOwnerList = new ArrayList<>();
        catOwnerList.add(new CatOwner(1, "Axzial"));
        catOwnerList.add(new CatOwner(2, "NextSap"));
        catOwnerList.add(new CatOwner(3, "Mohul_"));

        catOwnerReturnDtoList = new ArrayList<>();
        catOwnerReturnDtoList.add(new CatOwnerReturnDto());
        catOwnerReturnDtoList.add(new CatOwnerReturnDto());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOwners() throws Exception {
        Mockito.when(catOwnerService.findAllSimple()).thenReturn(catOwnerReturnDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getOwner() throws Exception {
        Mockito.when(catOwnerService.findByIdSimple(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(catOwnerReturnDtoList.get(0)));
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catOwnerService, Mockito.times(1)).findByIdSimple(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(catOwnerService);
    }

    @Test
    void addCatOwner() throws Exception {
        Mockito.when(catOwnerService.saveWithCatsId(ArgumentMatchers.any(CatOwnerWithCatsIdDto.class))).thenReturn(new CatOwnerReturnDto());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(new CatOwnerWithCatsIdDto()))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(catOwnerService, Mockito.times(1)).saveWithCatsId(ArgumentMatchers.any(CatOwnerWithCatsIdDto.class));
        Mockito.verifyNoMoreInteractions(catOwnerService);
    }

    @Test
    void update() throws Exception {
        Mockito.when(catOwnerService.updateWithCatsId(1, new CatOwnerWithCatsIdDto())).thenReturn(java.util.Optional.of(new CatOwnerReturnDto()));

        mockMvc.perform(MockMvcRequestBuilders.patch(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(new CatOwnerWithCatsIdDto()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catOwnerService, Mockito.times(1)).updateWithCatsId(ArgumentMatchers.anyLong(), ArgumentMatchers.any(CatOwnerWithCatsIdDto.class));
        Mockito.verifyNoMoreInteractions(catOwnerService);
    }

    @Test
    void delete() throws Exception {
        Mockito.doNothing().when(catOwnerService).delete(1);

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(catOwnerService, Mockito.times(1)).delete(ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(catOwnerService);
    }
}