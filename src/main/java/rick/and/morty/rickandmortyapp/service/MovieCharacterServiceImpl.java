package rick.and.morty.rickandmortyapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rick.and.morty.rickandmortyapp.dto.external.ExternalCharacterDto;
import rick.and.morty.rickandmortyapp.dto.external.ExternalResponseDto;
import rick.and.morty.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import rick.and.morty.rickandmortyapp.model.MovieCharacter;
import rick.and.morty.rickandmortyapp.repository.MovieCharacterRepository;

@Service
@RequiredArgsConstructor
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper mapper;

    @PostConstruct
    @Scheduled(cron = "0 8 * * * ?")
    public void syncExternalCharacters() {
        ExternalResponseDto externalResponseDto = httpClient.get("${api.url}",
                ExternalResponseDto.class);
        saveDtosToDb(externalResponseDto);
        while (externalResponseDto.getInfo().getNext() != null) {
            externalResponseDto = httpClient.get(externalResponseDto.getInfo().getNext(),
                    ExternalResponseDto.class);
            saveDtosToDb(externalResponseDto);
        }
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        long count = movieCharacterRepository.count();
        long randomId = (long) (Math.random() * count);
        return movieCharacterRepository.findById(randomId).orElseThrow();
    }

    @Override
    public List<MovieCharacter> findAllByNameContains(String namePart) {
        return movieCharacterRepository.findAllByNameContains(namePart);
    }

    private void saveDtosToDb(ExternalResponseDto externalResponseDto) {
        Map<Long, ExternalCharacterDto> externalDtos = Arrays.stream(externalResponseDto.getResults())
                .collect(Collectors.toMap(ExternalCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();
        List<MovieCharacter> existingCharacters = movieCharacterRepository
                .findAllByExternalIdIn(externalIds);
        Map<Long, MovieCharacter> existingCharactersWithIds = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingIds = existingCharactersWithIds.keySet();
        externalIds.removeAll(existingIds);
        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(i -> mapper.parseApiCharacterResponse(externalDtos.get(i)))
                .collect(Collectors.toList());
        movieCharacterRepository.saveAll(charactersToSave);
    }
}
