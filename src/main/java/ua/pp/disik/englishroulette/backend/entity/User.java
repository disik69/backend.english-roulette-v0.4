package ua.pp.disik.englishroulette.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(
        indexes = {
                @Index(columnList = "email", unique = true)
        }
)
@Data
@NoArgsConstructor
public class User implements UserDetails {
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

    private int readingCount;
    private int memoryCount;
    private int repeatTerm;
    private int lessonSize;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Exercise> exercises;

    public User(
            @NotNull String name,
            @NotNull String email,
            @NotNull String password,
            @NotNull Role role,
            int readingCount,
            int memoryCount,
            int repeatTerm,
            int lessonSize
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.readingCount = readingCount;
        this.memoryCount = memoryCount;
        this.repeatTerm = repeatTerm;
        this.lessonSize = lessonSize;
        this.exercises = new ArrayList<>();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(() -> "ROLE_" + role.toString());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
