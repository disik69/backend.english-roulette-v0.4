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
    public WordPageDto read(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return wordService.read(page, size);
    }

    @GetMapping("/{id}")
    public String read(@PathVariable int id) {
        return "read word";
    }

    @GetMapping("/search")
    public List<WordReadDto> search(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int size
    ) {
        return wordService.search(query, size);
    }

    @PostMapping()
    public WordReadDto create(@RequestBody WordWriteDto word) {
        return wordService.create(word);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id) {
        return "update word";
    }

    @DeleteMapping()
    public String delete() {
        return "delete words";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return "delete word";
    }
}
