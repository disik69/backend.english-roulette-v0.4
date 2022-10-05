package ua.pp.disik.englishroulette.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    @Id
    @Enumerated(EnumType.STRING)
    private SettingKey name;

    @NotNull
    private String value;
}
