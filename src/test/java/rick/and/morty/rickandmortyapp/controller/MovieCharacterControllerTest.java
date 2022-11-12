package rick.and.morty.rickandmortyapp.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import rick.and.morty.rickandmortyapp.model.MovieCharacter;
import rick.and.morty.rickandmortyapp.service.MovieCharacterService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieCharacterControllerTest {
    @MockBean
    private MovieCharacterService movieCharacterService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void getCharactersByName_Ok() {
        String namePart = "Tom";
        List<MovieCharacter> mockCharacters = new ArrayList<>();
        MovieCharacter mockCharacter = new MovieCharacter();
        mockCharacter.setName("Tom");
        mockCharacters.add(mockCharacter);
        Mockito.when(movieCharacterService.findAllByNameContains(namePart)).thenReturn(mockCharacters);
        RestAssuredMockMvc.given()
                .queryParam("namePart", namePart)
                .when()
                .get("/movie-characters/by-name")
                .then()
                .body("size()", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("Tom"));
    }
}
