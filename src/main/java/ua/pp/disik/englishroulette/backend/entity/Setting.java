package ua.pp.disik.englishroulette.backend.entity;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    @Id
    @Enumerated(EnumType.STRING)
    private SettingName name;

    @Nonnull
    private String value;
}
