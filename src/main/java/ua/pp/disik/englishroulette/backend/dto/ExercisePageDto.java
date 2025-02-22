package ua.pp.disik.englishroulette.backend.dto;

import lombok.Data;
import ua.pp.disik.englishroulette.backend.entity.Exercise;

import java.util.List;

@Data
public class ExercisePageDto {
    private List<Exercise> content;
    private int page;
    private int size;
    private int total;
}
