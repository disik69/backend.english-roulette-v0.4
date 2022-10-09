package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.pp.disik.englishroulette.backend.entity.Word;

import java.util.Optional;

public interface WordRepository extends PagingAndSortingRepository<Word, Integer> {
    Optional<Word> findByBody(String body);
    Page<Word> findByBodyContaining(String body, Pageable pageable);
}
