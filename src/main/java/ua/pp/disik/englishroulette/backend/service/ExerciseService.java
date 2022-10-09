package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.dao.ExerciseCreateDao;
import ua.pp.disik.englishroulette.backend.dao.ExerciseReadDao;
import ua.pp.disik.englishroulette.backend.dao.ExerciseStatusDao;
import ua.pp.disik.englishroulette.backend.entity.Exercise;
import ua.pp.disik.englishroulette.backend.entity.User;
import ua.pp.disik.englishroulette.backend.repository.ExerciseRepository;

import java.util.List;
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

    public ExerciseReadDao create(ExerciseCreateDao createDao, User user) {
        validationService.validate(createDao);

        Exercise exercise = new Exercise(user);
        BeanUtils.copyProperties(createDao, exercise);
        exercise.setForeignWords(wordService.findByIds(createDao.getForeignWordIds()));
        exercise.setNativeWords(wordService.findByIds(createDao.getNativeWordIds()));
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
}
