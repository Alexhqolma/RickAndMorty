package rick.and.morty.rickandmortyapp.dto.external;

import lombok.Data;

@Data
public class ExternalResponseDto {
    private ExternalInfoDto info;
    private ExternalCharacterDto[] results;
}
