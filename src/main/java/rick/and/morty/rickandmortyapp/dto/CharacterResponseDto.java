package rick.and.morty.rickandmortyapp.dto;

import lombok.Data;
import rick.and.morty.rickandmortyapp.model.Gender;
import rick.and.morty.rickandmortyapp.model.Status;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
