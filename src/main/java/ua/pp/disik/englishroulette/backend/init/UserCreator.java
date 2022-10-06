package ua.pp.disik.englishroulette.backend.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.pp.disik.englishroulette.backend.entity.*;
import ua.pp.disik.englishroulette.backend.service.SettingService;
import ua.pp.disik.englishroulette.backend.service.UserService;

import java.util.Map;

import static ua.pp.disik.englishroulette.backend.entity.SettingName.*;

@Component
@Order(20)
@Slf4j
public class UserCreator extends Creator {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SettingService settingService;

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
        Map<SettingName, String> settings = settingService.getMap();
        int readingCount = Integer.parseInt(settings.get(READING_COUNT));
        int memoryCount = Integer.parseInt(settings.get(MEMORY_COUNT));
        int repeatTerm = Integer.parseInt(settings.get(REPEAT_TERM));
        int lessonSize = Integer.parseInt(settings.get(LESSON_SIZE));

        Word nativeWord = new Word("cкала");
        Word foreignWord = new Word("rock");

        String userEmail = "user@user.test";
        User user = new User(
                userEmail, userEmail, passwordEncoder.encode(userEmail),
                Role.USER,
                readingCount, memoryCount, repeatTerm, lessonSize
        );
        Exercise exercise = new Exercise(user);
        exercise.getNativeWords().add(nativeWord);
        exercise.getForeignWords().add(foreignWord);
        user.getExercises().add(exercise);
        log.info(userService.repository().save(user).toString());

        String adminEmail = "root@root.test";
        User admin = new User(
                adminEmail, adminEmail, passwordEncoder.encode(adminEmail),
                Role.ADMIN,
                readingCount, memoryCount, repeatTerm, lessonSize
        );
        log.info(userService.repository().save(admin).toString());

    }
}
