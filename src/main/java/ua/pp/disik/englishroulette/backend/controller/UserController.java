package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "/user")
public class UserController {
    @GetMapping()
    @Parameter(name = "token")
    public String read() {
        return "read users";
    }

    @GetMapping("/{id}")
    @Parameter(name = "token")
    public String read(@PathVariable int id) {
        return "read user";
    }

    @PostMapping()
    @Parameter(name = "token")
    public String create() {
        return "create user";
    }

    @PutMapping("/{id}")
    @Parameter(name = "token")
    public String update(@PathVariable int id) {
        return "update user";
    }

    @DeleteMapping()
    @Parameter(name = "token")
    public String delete() {
        return "delete users";
    }

    @DeleteMapping("/{id}")
    @Parameter(name = "token")
    public String delete(@PathVariable int id) {
        return "delete user";
    }
}
