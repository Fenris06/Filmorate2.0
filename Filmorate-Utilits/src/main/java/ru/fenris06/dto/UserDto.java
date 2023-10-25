package ru.fenris06.dto;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;
import ru.fenris06.validation.Create;
import ru.fenris06.validation.Update;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserDto {
    @NotNull(message = "Field id can't be null if you update", groups = Update.class)
    private Long id;
    @NotBlank(message = "Field email can't be null, empty, ot only with whitespace", groups = {Update.class, Create.class})
    @Email(message = "Field email must be email pattern", groups = {Update.class, Create.class})
    private String email;
    @NotBlank(message = "Field login can't be null, empty, ot only with whitespace", groups = {Update.class, Create.class})
    private String login;
    @NotBlank(message = "Field name can't be null, empty, ot only with whitespace", groups = {Update.class, Create.class})
    private String name;
    @NotNull(message = "Field name can't be null", groups = {Update.class, Create.class})
    @Past(message = "Field birthday can't be in future", groups = {Update.class, Create.class})
    private LocalDate birthday;
}
