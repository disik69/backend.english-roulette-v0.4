package ua.pp.disik.englishroulette.backend.dao;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseReadDao {
    private int readingCount;
    private int memoryCount;
    private ExerciseStatusDao status;
    private List<WordReadDao> nativeWords;
    private List<WordReadDao> foreignWords;
}
