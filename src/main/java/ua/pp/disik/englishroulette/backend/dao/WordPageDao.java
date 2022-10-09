package ua.pp.disik.englishroulette.backend.dao;

import lombok.Data;

import java.util.List;

@Data
public class WordPageDao {
    private List<WordReadDao> content;
    private int page;
    private int size;
    private int total;
}
