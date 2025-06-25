package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ua.pp.disik.englishroulette.backend.dto.WordPageDto;
import ua.pp.disik.englishroulette.backend.dto.WordReadDto;
import ua.pp.disik.englishroulette.backend.dto.WordWriteDto;
import ua.pp.disik.englishroulette.backend.service.WordService;

import java.util.List;

@RestController
@RequestMapping("/word")
@Tag(name = "/word")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping()
    @Parameter(name = "token")
    public WordPageDto read(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return wordService.read(page, size);
    }

    @GetMapping("/{id}")
    @Parameter(name = "token")
    public String read(@PathVariable int id) {
        return "read word";
    }

    @GetMapping("/search")
    @Parameter(name = "token")
    public List<WordReadDto> search(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int size
    ) {
        return wordService.search(query, size);
    }

    @PostMapping()
    @Parameter(name = "token")
    public WordReadDto create(@RequestBody WordWriteDto word) {
        return wordService.create(word);
    }

    @PutMapping("/{id}")
    @Parameter(name = "token")
    public String update(@PathVariable int id) {
        return "update word";
    }

    @DeleteMapping()
    @Parameter(name = "token")
    public String delete() {
        return "delete words";
    }

    @DeleteMapping("/{id}")
    @Parameter(name = "token")
    public String delete(@PathVariable int id) {
        return "delete word";
    }
}
