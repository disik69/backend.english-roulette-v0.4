package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public String read() {
        return "read users";
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String read(@PathVariable Integer id) {
        return "read user";
    }

    @PostMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public String create() {
        return "create user";
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String update(@PathVariable Integer id) {
        return "update user";
    }

    @DeleteMapping()
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete() {
        return "delete users";
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "token", paramType = "query")
    public String delete(@PathVariable Integer id) {
        return "delete user";
    }
}
