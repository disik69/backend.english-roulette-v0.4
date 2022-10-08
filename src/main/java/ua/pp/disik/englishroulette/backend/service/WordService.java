package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.dao.WordReadDao;
import ua.pp.disik.englishroulette.backend.dao.WordWriteDao;
import ua.pp.disik.englishroulette.backend.entity.Word;
import ua.pp.disik.englishroulette.backend.repository.WordRepository;

@Service
public class WordService implements RepositoryService<WordRepository> {
    private final ValidationService validationService;
    private final WordRepository wordRepository;

    public WordService(
            ValidationService validationService,
            WordRepository wordRepository
    ) {
        this.validationService = validationService;
        this.wordRepository = wordRepository;
    }

    @Override
    public WordRepository repository() {
        return wordRepository;
    }

    public WordReadDao create(WordWriteDao wordWriteDao) {
        validationService.validate(wordWriteDao);

        Word word = new Word();
        BeanUtils.copyProperties(wordWriteDao, word);
        word = wordRepository.save(word);
        WordReadDao wordReadDao = new WordReadDao();
        BeanUtils.copyProperties(word, wordReadDao);

        return wordReadDao;
    }
}
