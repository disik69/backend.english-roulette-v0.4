package ua.pp.disik.englishroulette.backend.service;

import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.repository.WordRepository;

@Service
public class WordService implements RepositoryService<WordRepository> {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public WordRepository repository() {
        return wordRepository;
    }
}
