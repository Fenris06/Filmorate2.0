package ru.fenris06.repository.film;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fenris06.model.film.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}