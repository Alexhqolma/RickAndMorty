package rick.and.morty.rickandmortyapp.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rick.and.morty.rickandmortyapp.dto.CharacterResponseDto;
import rick.and.morty.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import rick.and.morty.rickandmortyapp.model.MovieCharacter;
import rick.and.morty.rickandmortyapp.service.MovieCharacterService;

@RestController
@RequestMapping("/movie-characters")
@RequiredArgsConstructor
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper mapper;

    @GetMapping("/random")
    @ApiOperation(value = "Show random character")
    public CharacterResponseDto getRandom() {
        MovieCharacter character = movieCharacterService.getRandomCharacter();
        return mapper.toResponseDto(character);
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get characters by name")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namePart) {
        return movieCharacterService.findAllByNameContains(namePart)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
