package ua.pp.disik.englishroulette.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int readingCount;
    private int memoryCount;
    private long checkedAt;
    private long updatedAt;

    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Word> nativeWords;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Word> foreignWords;

    public Exercise(
            int readingCount,
            int memoryCount
    ) {
        this.readingCount = readingCount;
        this.memoryCount = memoryCount;
        this.checkedAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
        this.nativeWords = new ArrayList<>();
        this.foreignWords = new ArrayList<>();
    }
}
