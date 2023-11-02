package ru.fenris06.validation.annatation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ReleaseValidator implements ConstraintValidator<Release, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isAfter(LocalDate.of(1895, 12, 28));
    }
}
