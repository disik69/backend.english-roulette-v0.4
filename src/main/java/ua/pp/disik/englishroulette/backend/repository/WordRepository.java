package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ua.pp.disik.englishroulette.backend.entity.Word;

import java.util.Optional;

public interface WordRepository extends CrudRepository<Word, Integer> {
    Optional<Word> findByBody(String body);
}
