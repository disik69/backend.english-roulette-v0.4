package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import ua.pp.disik.englishroulette.backend.dto.ExerciseWriteDto;
import ua.pp.disik.englishroulette.backend.dto.ExercisePageDto;
import ua.pp.disik.englishroulette.backend.dto.ExerciseReadDto;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.service.ExerciseService;

import java.util.List;

@Api
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExercisePageDto read(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return null;
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDto read(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return exerciseService.read(id, user);
    }

    @GetMapping("/search")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDto> search(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @RequestParam String query,
            @RequestParam(defaultValue = "10") int size
    ) {
        return null;
    }

    @GetMapping("/lesson/reading")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDto> getReading(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/memory")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDto> getMemory(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/check")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDto> getCheck(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDto create(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @RequestBody ExerciseWriteDto exercise
    ) {
        return exerciseService.create(exercise, user);
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDto update(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @PathVariable int id,
            @RequestBody ExerciseWriteDto exercise
    ) {
        return exerciseService.update(id, exercise, user);
    }

    @PutMapping("/{id}/pick-up")
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDto pickUp(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @PutMapping("/{id}/set-added")
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDto setAdded(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @PutMapping("/{id}/set-learned")
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDto setLearned(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @PathVariable int id
    ) {
        return null;
    }

    @DeleteMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete() {
        return "delete exercises";
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete(@PathVariable int id) {
        return "delete exercise";
    }
}
