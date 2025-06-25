package ua.pp.disik.englishroulette.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.disik.englishroulette.backend.entity.JwtToken;
import ua.pp.disik.englishroulette.backend.exception.HttpErrorException;
import ua.pp.disik.englishroulette.backend.service.AuthenticationService;

@RestController
@Tag(name = "/")
public class SignController {
    private final AuthenticationService authenticationService;

    public SignController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public JwtToken in(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        return authenticationService.signIn(email, password)
                .orElseThrow(() -> new HttpErrorException(400, "The email/password is invalid"));
    }
}
