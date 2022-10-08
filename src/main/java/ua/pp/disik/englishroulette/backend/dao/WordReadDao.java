package ua.pp.disik.englishroulette.backend.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WordReadDao {
    private int id;

    @NotNull
    private String body;
}
