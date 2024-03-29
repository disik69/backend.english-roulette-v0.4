package ua.pp.disik.englishroulette.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public Exercise(@NotNull User user) {
        this.user = user;
        this.readingCount = user.getReadingCount();
        this.memoryCount = user.getMemoryCount();
        this.checkedAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
        this.nativeWords = new ArrayList<>();
        this.foreignWords = new ArrayList<>();
    }
}
