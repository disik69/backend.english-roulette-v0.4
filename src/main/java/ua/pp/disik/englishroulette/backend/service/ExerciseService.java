package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.disik.englishroulette.backend.dao.ExerciseWriteDao;
import ua.pp.disik.englishroulette.backend.dao.ExerciseReadDao;
import ua.pp.disik.englishroulette.backend.dao.ExerciseStatusDao;
import ua.pp.disik.englishroulette.backend.entity.Exercise;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.entity.Word;
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
    public ExerciseReadDao create(ExerciseWriteDao dao, User user) {
        validationService.validate(dao);

        Exercise exercise = new Exercise(user);
        setWords(exercise, dao.getForeignWordIds(), dao.getNativeWordIds());
        exercise = exerciseRepository.save(exercise);

        return convertToReadDao(exercise);
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

    public ExerciseReadDao update(int id, ExerciseWriteDao dao, User user) {
        validationService.validate(dao);

        Exercise exercise = getUserExercise(exerciseRepository.findById(id), user);
        setWords(exercise, dao.getForeignWordIds(), dao.getNativeWordIds());
        exercise = exerciseRepository.save(exercise);

        return convertToReadDao(exercise);
    }

    public List<ExerciseReadDao> convertToReadDao(List<Exercise> exercises) {
        return exercises.stream().map(this::convertToReadDao).collect(Collectors.toList());
    }

    public ExerciseReadDao convertToReadDao(Exercise exercise) {
        ExerciseReadDao dao = new ExerciseReadDao();
        BeanUtils.copyProperties(exercise, dao);
        if (exercise.getUpdatedAt() >= exercise.getCheckedAt()) {
            dao.setStatus(ExerciseStatusDao.ADDED);
        } else {
            dao.setStatus(ExerciseStatusDao.LEARNED);
        }
        dao.setNativeWords(wordService.convertToReadDao(exercise.getNativeWords()));
        dao.setForeignWords(wordService.convertToReadDao(exercise.getForeignWords()));
        return dao;
    }

    @Transactional
    public ExerciseReadDao read(int id, User user) {
        return convertToReadDao(getUserExercise(exerciseRepository.findById(id), user));
    }
}
