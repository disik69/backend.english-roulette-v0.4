package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "/user")
public class UserController {
    @GetMapping()
    public String read() {
        return "read users";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable int id) {
        return "read user";
    }

    @PostMapping()
    public String create() {
        return "create user";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id) {
        return "update user";
    }

    @DeleteMapping()
    public String delete() {
        return "delete users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return "delete user";
    }
}
