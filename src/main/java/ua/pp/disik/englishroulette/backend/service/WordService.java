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
import java.util.stream.StreamSupport;

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

    public WordReadDao create(WordWriteDao dao) {
        validationService.validate(dao);

        Word word = new Word();
        BeanUtils.copyProperties(dao, word);
        word = wordRepository.save(word);

        return convertToReadDao(word);
    }

    public WordPageDao read(int page, int size) {
        Page<Word> words = wordRepository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size));

        WordPageDao pageDao = new WordPageDao();
        pageDao.setContent(convertToReadDao(words.getContent()));
        pageDao.setPage(words.getNumber() + 1);
        pageDao.setSize(words.getSize());
        pageDao.setTotal(words.getTotalPages());

        return pageDao;
    }

    public List<WordReadDao> search(String query, int size) {
        Page<Word> words = wordRepository.findByBodyContaining(query, PageRequest.of(0, size));

        return convertToReadDao(words.getContent());
    }

    public List<WordReadDao> convertToReadDao(List<Word> words) {
        return words.stream().map(this::convertToReadDao).collect(Collectors.toList());
    }

    public WordReadDao convertToReadDao(Word word) {
        WordReadDao dao = new WordReadDao();
        BeanUtils.copyProperties(word, dao);
        return dao;
    }

    public List<Word> findByIds(List<Integer> ids) {
        return StreamSupport.stream(wordRepository.findAllById(ids).spliterator(), false)
                .collect(Collectors.toList());
    }
}
