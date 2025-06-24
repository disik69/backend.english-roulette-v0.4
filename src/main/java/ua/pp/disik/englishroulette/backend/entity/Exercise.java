package ua.pp.disik.englishroulette.backend.entity;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Word> nativeWords;

    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Word> foreignWords;

    public Exercise(@Nonnull User user) {
        this.user = user;
        this.readingCount = user.getReadingCount();
        this.memoryCount = user.getMemoryCount();
        this.checkedAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
        this.nativeWords = new ArrayList<>();
        this.foreignWords = new ArrayList<>();
    }
}
