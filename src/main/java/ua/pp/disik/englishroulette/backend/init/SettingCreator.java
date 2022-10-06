package ua.pp.disik.englishroulette.backend.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.pp.disik.englishroulette.backend.entity.Setting;
import ua.pp.disik.englishroulette.backend.service.SettingService;

import static ua.pp.disik.englishroulette.backend.entity.SettingName.*;

@Component
@Order(10)
@Slf4j
public class SettingCreator extends Creator {
    private final SettingService settingService;

    @Autowired
    public SettingCreator(SettingService settingService) {
        this.settingService = settingService;
    }

    @Override
    public void create() {
        Setting readingCount = new Setting(READING_COUNT, "7");
        log.info(settingService.repository().save(readingCount).toString());

        Setting memoryCount = new Setting(MEMORY_COUNT, "7");
        log.info(settingService.repository().save(memoryCount).toString());

        Setting repeatTerm = new Setting(REPEAT_TERM, "7");
        log.info(settingService.repository().save(repeatTerm).toString());

        Setting lessonSize = new Setting(LESSON_SIZE, "30");
        log.info(settingService.repository().save(lessonSize).toString());
    }
}
