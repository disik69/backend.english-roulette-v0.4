package ua.pp.disik.englishroulette.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class WordPageDto {
    private List<WordReadDto> content;
    private int page;
    private int size;
    private int total;
}
