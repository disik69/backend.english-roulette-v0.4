package ua.pp.disik.englishroulette.backend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/word")
public class WordController {
    @GetMapping()
    String read() {
        return "read words";
    }

    @GetMapping("/{id}")
    String read(@PathVariable Integer id) {
        return "read word";
    }

    @PostMapping()
    String create() {
        return "create word";
    }

    @PutMapping("/{id}")
    String update(@PathVariable Integer id) {
        return "update word";
    }

    @DeleteMapping()
    String delete() {
        return "delete words";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id) {
        return "delete word";
    }
}
