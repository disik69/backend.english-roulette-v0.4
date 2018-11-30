package ua.pp.disik.englishroulette.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        indexes = {@Index(columnList = "email", unique = true)}
        )
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(@NotNull String name,
                @NotNull String email,
                @NotNull String password,
                @NotNull Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
