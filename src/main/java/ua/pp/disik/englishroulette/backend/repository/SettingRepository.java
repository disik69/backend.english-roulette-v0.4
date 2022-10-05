package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ua.pp.disik.englishroulette.backend.entity.Setting;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
