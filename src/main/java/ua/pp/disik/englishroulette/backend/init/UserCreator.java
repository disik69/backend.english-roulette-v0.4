package ua.pp.disik.englishroulette.backend.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.pp.disik.englishroulette.backend.entity.Role;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.service.UserService;

@Component
@Slf4j
public class UserCreator implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCreator(UserService userService,
                       PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean runnable = Boolean.parseBoolean(System.getProperty("user-creator"));

        if (runnable) {
            String userEmail = "user@user.test";
            User user = new User(userEmail, userEmail, passwordEncoder.encode(userEmail), Role.USER);
            String adminEmail = "root@root.test";
            User admin = new User(adminEmail, adminEmail, passwordEncoder.encode(adminEmail), Role.ADMIN);

            log.info(userService.save(user).toString());
            log.info(userService.save(admin).toString());
        }
    }
}
