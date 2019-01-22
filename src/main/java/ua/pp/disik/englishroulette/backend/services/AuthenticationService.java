package ua.pp.disik.englishroulette.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.entities.User;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JWTTokenService jwtTokenService;

    @Autowired
    public AuthenticationService(UserService userService, JWTTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    public Optional<User> findByToken(String token) {
        Integer id = new Integer(jwtTokenService.verify(token).get("id"));

        return userService.findById(id);
    }
}
