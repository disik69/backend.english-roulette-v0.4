package ua.pp.disik.englishroulette.backend.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @GetMapping()
    String read() {
        return "read exercises";
    }

    @GetMapping("/{id}")
    String read(@PathVariable Integer id) {
        return "read exercise";
    }

    @PostMapping()
    String create() {
        return "create exercise";
    }

    @PutMapping("/{id}")
    String update(@PathVariable Integer id) {
        return "update exercise";
    }

    @DeleteMapping()
    String delete() {
        return "delete exercises";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id) {
        return "delete exercise";
    }
}
