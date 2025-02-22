package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.disik.englishroulette.backend.dto.ExerciseWriteDto;
import ua.pp.disik.englishroulette.backend.dto.ExerciseReadDto;
import ua.pp.disik.englishroulette.backend.dto.ExerciseStatusDto;
import ua.pp.disik.englishroulette.backend.entity.Exercise;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.exception.HttpErrorException;
import ua.pp.disik.englishroulette.backend.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService implements RepositoryService<ExerciseRepository> {
    private final ValidationService validationService;
    private final ExerciseRepository exerciseRepository;
    private final WordService wordService;

    public ExerciseService(
            ValidationService validationService,
            ExerciseRepository exerciseRepository,
            WordService wordService
    ) {
        this.validationService = validationService;
        this.exerciseRepository = exerciseRepository;
        this.wordService = wordService;
    }

    @Override
    public ExerciseRepository repository() {
        return exerciseRepository;
    }

    @Transactional
    public ExerciseReadDto create(ExerciseWriteDto dto, User user) {
        validationService.validate(dto);

        Exercise exercise = new Exercise(user);
        setWords(exercise, dto.getForeignWordIds(), dto.getNativeWordIds());
        exercise = exerciseRepository.save(exercise);

        return convertToReadDto(exercise);
    }

    private Exercise getUserExercise(Optional<Exercise> optionalExercise, User user) {
        Exercise exercise = optionalExercise
                .orElseThrow(() -> new HttpErrorException(404, "Exercise is not found"));
        if (! exercise.getUser().equals(user)) {
            throw new HttpErrorException(403, "Forbidden");
        }
        return exercise;
    }

    protected void setWords(Exercise exercise, List<Integer> foreignWordIds, List<Integer> nativeWordIds) {
        exercise.setForeignWords(wordService.findByIds(foreignWordIds));
        exercise.setNativeWords(wordService.findByIds(nativeWordIds));
    }

    public ExerciseReadDto update(int id, ExerciseWriteDto dto, User user) {
        validationService.validate(dto);

        Exercise exercise = getUserExercise(exerciseRepository.findById(id), user);
        setWords(exercise, dto.getForeignWordIds(), dto.getNativeWordIds());
        exercise = exerciseRepository.save(exercise);

        return convertToReadDto(exercise);
    }

    public List<ExerciseReadDto> convertToReadDto(List<Exercise> exercises) {
        return exercises.stream().map(this::convertToReadDto).collect(Collectors.toList());
    }

    public ExerciseReadDto convertToReadDto(Exercise exercise) {
        ExerciseReadDto dto = new ExerciseReadDto();
        BeanUtils.copyProperties(exercise, dto);
        if (exercise.getUpdatedAt() >= exercise.getCheckedAt()) {
            dto.setStatus(ExerciseStatusDto.ADDED);
        } else {
            dto.setStatus(ExerciseStatusDto.LEARNED);
        }
        dto.setNativeWords(wordService.convertToReadDto(exercise.getNativeWords()));
        dto.setForeignWords(wordService.convertToReadDto(exercise.getForeignWords()));
        return dto;
    }

    @Transactional
    public ExerciseReadDto read(int id, User user) {
        return convertToReadDto(getUserExercise(exerciseRepository.findById(id), user));
    }
}
