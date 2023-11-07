package ru.fenris06.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GenreDto {
    @NotNull(message = "Field id can't be null if you update")
    private Long id;
    private String name;
}
