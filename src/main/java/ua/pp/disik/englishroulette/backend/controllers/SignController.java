package ua.pp.disik.englishroulette.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.disik.englishroulette.backend.entities.JwtToken;
import ua.pp.disik.englishroulette.backend.exceptions.HttpBadRequestException;
import ua.pp.disik.englishroulette.backend.services.AuthenticationService;

@RestController
public class SignController {
    private final AuthenticationService authenticationService;

    @Autowired
    public SignController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public JwtToken in(@RequestParam("email") String email,
                       @RequestParam("password") String password) {
        return authenticationService.signIn(email, password)
                .orElseThrow(() -> new HttpBadRequestException("The email/password is invalid"));
    }
}
