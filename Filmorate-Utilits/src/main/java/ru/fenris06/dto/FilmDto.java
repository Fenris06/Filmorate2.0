package ru.fenris06.dto;


import lombok.Getter;
import lombok.Setter;
import ru.fenris06.validation.Create;
import ru.fenris06.validation.Release;
import ru.fenris06.validation.Update;


import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class FilmDto {
    @NotNull(message = "Field id can't be null if you update", groups = Update.class)
    private Long Id;
    @NotBlank(message = "Field name can't be null, empty or only with whitespace", groups = {Update.class, Create.class})
    private String name;
    @NotBlank(message = "Field description can't be null, empty, ot only with whitespace", groups = {Update.class, Create.class})
    @Size(max = 200, message = "Field description can't have size more that 200 chars", groups = {Update.class, Create.class})
    private String description;
    @NotNull(message = "Field releaseDate can't be null if you update", groups = {Update.class, Create.class})
    @Release(message = "Field releaseDate can't be before 28.12.1985", groups = {Update.class, Create.class})
    private LocalDate releaseDate;
    @NotNull(message = "Field id can't be null if you update", groups = {Update.class, Create.class})
    @Positive(message = "Field id must be positive", groups = {Update.class, Create.class})
    private Integer duration;
}
