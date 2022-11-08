package rick.and.morty.rickandmortyapp.dto.mapper;

import org.springframework.stereotype.Component;
import rick.and.morty.rickandmortyapp.dto.CharacterResponseDto;
import rick.and.morty.rickandmortyapp.dto.external.ApiCharacterDto;
import rick.and.morty.rickandmortyapp.model.Gender;
import rick.and.morty.rickandmortyapp.model.MovieCharacter;
import rick.and.morty.rickandmortyapp.model.Status;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiCharacterResponse(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(dto.getName());
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setExternalId(dto.getId());
        return movieCharacter;
    }

    public CharacterResponseDto toResponseDto(MovieCharacter movieCharacter) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setExternalId(movieCharacter.getExternalId());
        dto.setGender(movieCharacter.getGender());
        dto.setStatus(movieCharacter.getStatus());
        dto.setName(movieCharacter.getName());
        dto.setId(movieCharacter.getId());
        return dto;
    }
}
