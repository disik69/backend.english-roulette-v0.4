package ua.pp.disik.englishroulette.backend.dao;

import lombok.Data;
import ua.pp.disik.englishroulette.backend.validation.WordBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class WordWriteDao {
    @NotNull
    @NotEmpty
    @WordBody(message = "value is duplicate")
    private String body;
}
