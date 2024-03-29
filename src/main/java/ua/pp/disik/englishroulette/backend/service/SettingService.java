package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.entity.SettingName;
import ua.pp.disik.englishroulette.backend.repository.SettingRepository;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SettingService implements RepositoryService<SettingRepository> {
    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public SettingRepository repository() {
        return settingRepository;
    }

    public Map<SettingName, String> getMap() {
        return StreamSupport.stream(settingRepository.findAll().spliterator(), false)
                .collect(Collectors.toMap(setting -> setting.getName(), setting -> setting.getValue()));
    }
}
