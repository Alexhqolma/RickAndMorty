package rick.and.morty.rickandmortyapp.service;

import java.util.List;
import rick.and.morty.rickandmortyapp.model.MovieCharacter;

public interface MovieCharacterService {

    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContains(String namePart);
}
