package ua.pp.disik.englishroulette.backend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/word")
public class WordController {
    @GetMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String read() {
        return "read words";
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String read(@PathVariable Integer id) {
        return "read word";
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    String create() {
        return "create word";
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
