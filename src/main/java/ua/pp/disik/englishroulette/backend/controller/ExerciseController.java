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
    @Parameter(name = "token")
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
    @Parameter(name = "token")
    public ExerciseReadDto read(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return exerciseService.read(id, user);
    }

    @GetMapping("/search")
    @Parameter(name = "token")
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
    @Parameter(name = "token")
    public List<ExerciseReadDto> getReading(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/memory")
    @Parameter(name = "token")
    public List<ExerciseReadDto> getMemory(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/check")
    @Parameter(name = "token")
    public List<ExerciseReadDto> getCheck(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @PostMapping()
    @Parameter(name = "token")
    public ExerciseReadDto create(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @RequestBody ExerciseWriteDto exercise
    ) {
        return exerciseService.create(exercise, user);
    }

    @PutMapping("/{id}")
    @Parameter(name = "token")
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
    @Parameter(name = "token")
    public ExerciseReadDto pickUp(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @PutMapping("/{id}/set-added")
    @Parameter(name = "token")
    public ExerciseReadDto setAdded(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @PutMapping("/{id}/set-learned")
    @Parameter(name = "token")
    public ExerciseReadDto setLearned(
            @Parameter(hidden = true)
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @DeleteMapping()
    @Parameter(name = "token")
    public String delete() {
        return "delete exercises";
    }

    @DeleteMapping("/{id}")
    @Parameter(name = "token")
    public String delete(@PathVariable int id) {
        return "delete exercise";
    }
}
