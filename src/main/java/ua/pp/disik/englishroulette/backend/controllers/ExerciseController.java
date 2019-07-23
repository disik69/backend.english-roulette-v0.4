package ua.pp.disik.englishroulette.backend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @GetMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String read() {
        return "read exercises";
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
