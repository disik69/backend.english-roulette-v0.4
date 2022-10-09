package ua.pp.disik.englishroulette.backend.dao;

import lombok.Data;
import ua.pp.disik.englishroulette.backend.entity.Exercise;

import java.util.List;

@Data
public class ExercisePageDao {
    private List<Exercise> content;
    private int page;
    private int size;
    private int total;
}
