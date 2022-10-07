package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;
import ua.pp.disik.englishroulette.backend.entity.Word;
import ua.pp.disik.englishroulette.backend.service.WordService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Api
@RestController
@RequestMapping("/word")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    List<Word> read() {
        return StreamSupport.stream(
                wordService.repository().findAll().spliterator(),
                false
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String read(@PathVariable Integer id) {
        return "read word";
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    Word create(@RequestBody Word word) {
        return wordService.repository().save(word);
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String update(@PathVariable Integer id) {
        return "update word";
    }

    @DeleteMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String delete() {
        return "delete words";
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String delete(@PathVariable Integer id) {
        return "delete word";
    }
}
