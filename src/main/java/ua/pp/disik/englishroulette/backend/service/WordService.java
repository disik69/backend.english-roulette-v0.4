package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.dto.WordPageDto;
import ua.pp.disik.englishroulette.backend.dto.WordReadDto;
import ua.pp.disik.englishroulette.backend.dto.WordWriteDto;
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

    public WordReadDto create(WordWriteDto dto) {
        validationService.validate(dto);

        Word word = new Word();
        BeanUtils.copyProperties(dto, word);
        word = wordRepository.save(word);

        return convertToReadDto(word);
    }

    public WordPageDto read(int page, int size) {
        Page<Word> words = wordRepository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size));

        WordPageDto pageDto = new WordPageDto();
        pageDto.setContent(convertToReadDto(words.getContent()));
        pageDto.setPage(words.getNumber() + 1);
        pageDto.setSize(words.getSize());
        pageDto.setTotal(words.getTotalPages());

        return pageDto;
    }

    public List<WordReadDto> search(String query, int size) {
        Page<Word> words = wordRepository.findByBodyContaining(query, PageRequest.of(0, size));

        return convertToReadDto(words.getContent());
    }

    public List<WordReadDto> convertToReadDto(List<Word> words) {
        return words.stream().map(this::convertToReadDto).collect(Collectors.toList());
    }

    public WordReadDto convertToReadDto(Word word) {
        WordReadDto dto = new WordReadDto();
        BeanUtils.copyProperties(word, dto);
        return dto;
    }

    public List<Word> findByIds(List<Integer> ids) {
        return StreamSupport.stream(wordRepository.findAllById(ids).spliterator(), false)
                .collect(Collectors.toList());
    }
}
