package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.pp.disik.englishroulette.backend.entity.Setting;

public interface SettingRepository extends PagingAndSortingRepository<Setting, Integer> {}
