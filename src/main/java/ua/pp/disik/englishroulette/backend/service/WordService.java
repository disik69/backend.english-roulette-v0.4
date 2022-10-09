package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.dao.WordPageDao;
import ua.pp.disik.englishroulette.backend.dao.WordReadDao;
import ua.pp.disik.englishroulette.backend.dao.WordWriteDao;
import ua.pp.disik.englishroulette.backend.entity.Word;
import ua.pp.disik.englishroulette.backend.repository.WordRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        WordReadDao dao = new WordReadDao();
        BeanUtils.copyProperties(word, dao);

        return dao;
    }

    public WordPageDao read(int page, int size) {
        Page<Word> words = wordRepository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size));

        WordPageDao pageDao = new WordPageDao();
        pageDao.setContent(words.getContent().stream().map(word -> {
            WordReadDao wordDao = new WordReadDao();
            BeanUtils.copyProperties(word, wordDao);
            return wordDao;
        }).collect(Collectors.toList()));
        pageDao.setPage(words.getNumber() + 1);
        pageDao.setSize(words.getSize());
        pageDao.setTotal(words.getTotalPages());

        return pageDao;
    }

    public List<WordReadDao> search(String query, int size) {
        Page<Word> words = wordRepository.findByBodyContaining(query, PageRequest.of(0, size));

        return words.getContent().stream().map(word -> {
            WordReadDao dao = new WordReadDao();
            BeanUtils.copyProperties(word, dao);
            return dao;
        }).collect(Collectors.toList());
    }
}
