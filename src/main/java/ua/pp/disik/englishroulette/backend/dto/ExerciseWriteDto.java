package ua.pp.disik.englishroulette.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ExerciseWriteDto {
    @NotNull
    @Size(min = 1, max = 5)
    private List<Integer> nativeWordIds;

    @NotNull
    @Size(min = 1, max = 5)
    private List<Integer> foreignWordIds;
}
