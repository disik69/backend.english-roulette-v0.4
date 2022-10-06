package ua.pp.disik.englishroulette.backend.service;

import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.repository.ExerciseRepository;

@Service
public class ExerciseService implements RepositoryService<ExerciseRepository> {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository userRepository) {
        this.exerciseRepository = userRepository;
    }

    @Override
    public ExerciseRepository repository() {
        return exerciseRepository;
    }
}
