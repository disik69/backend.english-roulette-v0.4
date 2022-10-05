package ua.pp.disik.englishroulette.backend.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.pp.disik.englishroulette.backend.entity.Role;
import ua.pp.disik.englishroulette.backend.entity.SettingKey;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.service.SettingService;
import ua.pp.disik.englishroulette.backend.service.UserService;

import java.util.Map;

import static ua.pp.disik.englishroulette.backend.entity.SettingKey.*;

@Component
@Order(20)
@Slf4j
public class UserCreator extends Creator {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SettingService settingService;

    @Autowired
    public UserCreator(
            UserService userService,
            PasswordEncoder passwordEncoder,
            SettingService settingService
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.settingService = settingService;
    }

    @Override
    public void create() {
        Map<SettingKey, String> settings = settingService.getMap();
        int readingCount = Integer.parseInt(settings.get(READING_COUNT));
        int memoryCount = Integer.parseInt(settings.get(MEMORY_COUNT));
        int repeatTerm = Integer.parseInt(settings.get(REPEAT_TERM));
        int lessonSize = Integer.parseInt(settings.get(LESSON_SIZE));

        String userEmail = "user@user.test";
        User user = new User(
                userEmail, userEmail, passwordEncoder.encode(userEmail),
                Role.USER,
                readingCount, memoryCount, repeatTerm, lessonSize
        );
        String adminEmail = "root@root.test";
        User admin = new User(
                adminEmail, adminEmail, passwordEncoder.encode(adminEmail),
                Role.ADMIN,
                readingCount, memoryCount, repeatTerm, lessonSize
        );

        log.info(userService.repository().save(user).toString());
        log.info(userService.repository().save(admin).toString());
    }
}
