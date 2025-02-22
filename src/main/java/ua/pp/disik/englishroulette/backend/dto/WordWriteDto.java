package ua.pp.disik.englishroulette.backend.dto;

import lombok.Data;
import ua.pp.disik.englishroulette.backend.validation.WordBody;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class WordWriteDto {
    @NotNull
    @Size(min = 1, max = 255)
    @WordBody(message = "value is duplicate")
    private String body;
}
