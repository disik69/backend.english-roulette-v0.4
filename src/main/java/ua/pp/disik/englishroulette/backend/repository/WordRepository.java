package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ua.pp.disik.englishroulette.backend.entity.Word;

public interface WordRepository extends CrudRepository<Word, Integer> {
}
