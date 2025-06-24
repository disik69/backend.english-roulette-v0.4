package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.pp.disik.englishroulette.backend.entity.Exercise;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer>, PagingAndSortingRepository<Exercise, Integer> {
    List<Exercise> findByUserId(Integer userId);
}
