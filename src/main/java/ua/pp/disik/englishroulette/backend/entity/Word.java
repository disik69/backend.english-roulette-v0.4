package ua.pp.disik.englishroulette.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        indexes = {
                @Index(columnList = "body", unique = true)
        }
)
@Data
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String body;

    public Word(String body) {
        this.body = body;
    }
}
