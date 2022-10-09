package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import ua.pp.disik.englishroulette.backend.dao.ExerciseCreateDao;
import ua.pp.disik.englishroulette.backend.dao.ExercisePageDao;
import ua.pp.disik.englishroulette.backend.dao.ExerciseReadDao;
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
    public ExercisePageDao read(
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
    public String read(@PathVariable Integer id) {
        return "read exercise";
    }

    @GetMapping("/search")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDao> search(
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
    public List<ExerciseReadDao> getReading(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/memory")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDao> getMemory(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @GetMapping("/lesson/check")
    @ApiImplicitParam(name = "token", paramType = "query")
    public List<ExerciseReadDao> getCheck(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return null;
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public ExerciseReadDao create(
            @ApiIgnore
            @AuthenticationPrincipal
            User user,

            @RequestBody ExerciseCreateDao exercise
    ) {
        return exerciseService.create(exercise, user);
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String update(@PathVariable Integer id) {
        return "update exercise";
    }

    @DeleteMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete() {
        return "delete exercises";
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete(@PathVariable Integer id) {
        return "delete exercise";
    }
}
