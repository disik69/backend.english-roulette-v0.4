package ua.pp.disik.englishroulette.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

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