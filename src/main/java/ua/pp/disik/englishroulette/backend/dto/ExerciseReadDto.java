package ua.pp.disik.englishroulette.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseReadDto {
    private int id;
    private int readingCount;
    private int memoryCount;
    private ExerciseStatusDto status;
    private List<WordReadDto> nativeWords;
    private List<WordReadDto> foreignWords;
}
