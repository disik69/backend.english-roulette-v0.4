package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.pp.disik.englishroulette.backend.dto.ExerciseWriteDto;
import ua.pp.disik.englishroulette.backend.dto.ExercisePageDto;
import ua.pp.disik.englishroulette.backend.dto.ExerciseReadDto;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@Tag(name = "/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping()
    public ExercisePageDto read(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return null;
    }

    @GetMapping("/{id}")
    public ExerciseReadDto read(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return exerciseService.read(id, user);
    }

    @GetMapping("/search")
    public List<ExerciseReadDto> search(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @RequestParam String query,
            @RequestParam(defaultValue = "10") int size
    ) {
        return null;
    }

    @GetMapping("/lesson/reading")
    public List<ExerciseReadDto> getReading(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/memory")
    public List<ExerciseReadDto> getMemory(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/check")
    public List<ExerciseReadDto> getCheck(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @PostMapping()
    public ExerciseReadDto create(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @RequestBody ExerciseWriteDto exercise
    ) {
        return exerciseService.create(exercise, user);
    }

    @PutMapping("/{id}")
    public ExerciseReadDto update(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id,
            @RequestBody ExerciseWriteDto exercise
    ) {
        return exerciseService.update(id, exercise, user);
    }

    @PutMapping("/{id}/pick-up")
    public ExerciseReadDto pickUp(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @PutMapping("/{id}/set-added")
    public ExerciseReadDto setAdded(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @PutMapping("/{id}/set-learned")
    public ExerciseReadDto setLearned(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @DeleteMapping()
    public String delete() {
        return "delete exercises";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return "delete exercise";
    }
}
