package ua.pp.disik.englishroulette.backend.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ExerciseUpdateDao {
    @NotNull
    private ExerciseStatusDao status;

    @NotNull
    @Size(min = 1, max = 5)
    private List<Integer> nativeWordIds;

    @NotNull
    @Size(min = 1, max = 5)
    private List<Integer> foreignWordIds;
}
