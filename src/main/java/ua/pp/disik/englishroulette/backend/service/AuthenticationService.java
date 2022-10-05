package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.entity.JwtToken;
import ua.pp.disik.englishroulette.backend.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JWTTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserService userService, JWTTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByToken(JwtToken token) {
        return Optional.ofNullable(jwtTokenService.verify(token).get("id"))
                .map(Integer::valueOf)
                .flatMap(userService::findById);
    }

    public Optional<JwtToken> signIn(String email, String password) {
        return userService.findByEmail(email)
                .filter(value -> passwordEncoder.matches(password, value.getPassword()))
                .map(value -> {
                    Map<String, String> userData = new HashMap<>();
                    userData.put("id", value.getId().toString());

                    return jwtTokenService.expiring(userData);
                });
    }
}
