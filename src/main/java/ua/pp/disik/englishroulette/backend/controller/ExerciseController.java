package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import ua.pp.disik.englishroulette.backend.entity.Exercise;
import ua.pp.disik.englishroulette.backend.entity.User;

import java.util.List;

@Api
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @GetMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    List<Exercise> read(
            @ApiIgnore
            @AuthenticationPrincipal
            User user
    ) {
        return user.getExercises();
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String read(@PathVariable Integer id) {
        return "read exercise";
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String create() {
        return "create exercise";
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String update(@PathVariable Integer id) {
        return "update exercise";
    }

    @DeleteMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String delete() {
        return "delete exercises";
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String delete(@PathVariable Integer id) {
        return "delete exercise";
    }
}
