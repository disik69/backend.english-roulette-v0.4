package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;
import ua.pp.disik.englishroulette.backend.dao.WordPageDao;
import ua.pp.disik.englishroulette.backend.dao.WordReadDao;
import ua.pp.disik.englishroulette.backend.dao.WordWriteDao;
import ua.pp.disik.englishroulette.backend.service.WordService;

import java.util.List;

@Api
@RestController
@RequestMapping("/word")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public WordPageDao read(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return wordService.read(page, size);
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String read(@PathVariable Integer id) {
        return "read word";
    }

    @GetMapping("/search")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<WordReadDao> search(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int size
    ) {
        return wordService.search(query, size);
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public WordReadDao create(@RequestBody WordWriteDao word) {
        return wordService.create(word);
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String update(@PathVariable Integer id) {
        return "update word";
    }

    @DeleteMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete() {
        return "delete words";
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete(@PathVariable Integer id) {
        return "delete word";
    }
}
